package my.project.prototype.homeService;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.project.prototype.dataService.DataServiceInterface;
import my.project.prototype.latexService.LatexServiceInterface;
import my.project.prototype.models.User;
import my.project.prototype.persistenceService.PersistenceServiceInterface;

@Service
public class HomeServiceImpl implements HomeServiceInterface {

	@Autowired
	private PersistenceServiceInterface persistenceService;
	@Autowired
	private DataServiceInterface dataService;
	@Autowired
	private LatexServiceInterface latexService;

	@Override
	public void generateCV(User user) throws IOException {
		loadUserData(user);
		if (!saveUserToDatabase(user)) {
			throw new IOException("Can't save user, check persistence service");
		}

		processTemplates(user);
	}

	private void loadUserData(User user) throws IOException {
		user.setEducation(dataService
				.readEducationJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\education.json"));
		user.setWorkExperiences(dataService
				.readExperienceJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\experience.json"));
		user.setSkills(dataService.readTechnicalSkillsJsonData(
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\technicalSkills.json"));
	}

	private boolean saveUserToDatabase(User user) {
		return persistenceService.saveUser(user);
	}

	private void processTemplates(User user) throws IOException {
		String templateTechSkill = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\technicalSkills.txt";
		String templatePathExperience = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\experience.txt";
		String templatePathEducation = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\education.txt";
		String templatePathMain = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt";
		String targetMain = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main_finalized.txt";
		String targetEducation = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\education_finalized.txt";
		String targetExperience = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\workexperience_finalized.txt";
		String targetTechSkill = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\technicalSkills_finalized.txt";

		Map<String, String> userInfo = dataService.buildMapper(user);
		Map<String, String> experienceInfo = dataService.buildExperienceMapper(user);
		Map<String, String> educationInfo = dataService.buildEducationMapper(user);
		Map<String, String> techSkillInfo = dataService.buildTechnicalSkillsMapper(user);

		boolean isMainProcessed = latexService.processTemplate(templatePathMain, targetMain, userInfo);
		boolean isExperienceProcessed = latexService.processTemplate(templatePathExperience, targetExperience,
				experienceInfo);
		boolean isEducationProcessed = latexService.processTemplate(templatePathEducation, targetEducation,
				educationInfo);
		boolean isTechSkillProcessed = latexService.processTemplate(templateTechSkill, targetTechSkill, techSkillInfo);

		if (!targetMain.endsWith(".tex")) {
			targetMain = targetMain.replaceAll("\\.\\w+$", "") + ".tex";
		}
		latexService.compileLatex(new java.io.File(targetMain));

		logTemplateProcessingResults(isMainProcessed, isExperienceProcessed, isEducationProcessed,
				isTechSkillProcessed);
	}

	private void logTemplateProcessingResults(boolean isMainProcessed, boolean isExperienceProcessed,
			boolean isEducationProcessed, boolean isTechSkillProcessed) {
		if (isMainProcessed && isExperienceProcessed && isEducationProcessed && isTechSkillProcessed) {
			System.out.println("Template processed successfully!");
		} else {
			if (!isMainProcessed) {
				System.out.println("CV generation failed: Main template processing failed!");
			}
			if (!isExperienceProcessed) {
				System.out.println("CV generation failed: Experience template processing failed!");
			}
			if (!isEducationProcessed) {
				System.out.println("CV generation failed: Education template processing failed!");
			}
			if (!isTechSkillProcessed) {
				System.out.println("CV generation failed: Technical skills template processing failed!");
			}
		}
	}
}