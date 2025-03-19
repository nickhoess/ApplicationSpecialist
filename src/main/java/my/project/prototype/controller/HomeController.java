package my.project.prototype.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import my.project.prototype.dataService.DataServiceImpl;
import my.project.prototype.latexService.LatexServiceInterface;
import my.project.prototype.models.User;
import my.project.prototype.persistenceService.PersistenceServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private PersistenceServiceImpl persistenceService;
	@Autowired
	private DataServiceImpl dataService;
	@Autowired
	private LatexServiceInterface latexService;

	public void generateCV(User user) throws IOException {
		loadUserData(user);
		if (!saveUserToDatabase(user)) {
			System.out.println("Can't save user, check persistence service");
			return;
		}

		processTemplates(user);
	}

	private void loadUserData(User user) throws IOException {
		user.setEducation(dataService
				.readEducationJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\education.json"));
		user.setWorkExperiences(dataService
				.readExperienceJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\experience.json"));
	}

	private boolean saveUserToDatabase(User user) {
		return persistenceService.saveUser(user);
	}

	private void processTemplates(User user) throws IOException {
		String templatePathExperience = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\experience.txt";
		String templatePathEducation = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\education.txt";
		String templatePathMain = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt";
		String targetMain = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main_finalized.txt";
		String targetEducation = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\education_finalized.txt";
		String targetExperience = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\worksexperience_finalized.txt";

		Map<String, String> userInfo = dataService.buildMapper(user);
		Map<String, String> experienceInfo = dataService.buildExperienceMapper(user);
		Map<String, String> educationInfo = dataService.buildEducationMapper(user);

		boolean isMainProcessed = latexService.processTemplate(templatePathMain, targetMain, userInfo);
		boolean isExperienceProcessed = latexService.processTemplate(templatePathExperience, targetExperience,
				experienceInfo);
		boolean isEducationProcessed = latexService.processTemplate(templatePathEducation, targetEducation,
				educationInfo);

		logTemplateProcessingResults(isMainProcessed, isExperienceProcessed, isEducationProcessed);
	}

	private void logTemplateProcessingResults(boolean isMainProcessed, boolean isExperienceProcessed,
			boolean isEducationProcessed) {
		if (isMainProcessed && isExperienceProcessed && isEducationProcessed) {
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
		}
	}
}