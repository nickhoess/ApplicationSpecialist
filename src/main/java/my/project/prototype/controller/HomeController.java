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
		user.setEducation(dataService
				.readEducationJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\education.json"));
		user.setWorkExperiences(dataService
				.readExperienceJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\experience.json"));
		// Save to DB
		if (!persistenceService.saveUser(user)) {
			System.out.println("Can't save user, check persistence service");
			return;
		}

		// Delegate the processing of the template and compilation to the LatexService
		String templatePathexperience = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\experience.txt";
		String templatePatheducation = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\education.txt";
		String templatePathmain = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt";
		String targetMain = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main_finalized.txt";
		String targetEducation = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\education_finalized.txt";
		String targetExperience = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\worksexperience_finalized.txt";
		Map<String, String> userInfo = dataService.buildMapper(user);
		Map<String, String> experienceInfo = dataService.buildExperienceMapper(user);
		Map<String, String> educationInfo = dataService.buildEducationMapper(user);

		boolean templateIsProcessedMain = latexService.processTemplate(templatePathmain, targetMain, userInfo);
		boolean templateIsProcessedExperience = latexService.processTemplate(templatePathexperience, targetExperience,
				experienceInfo);
		boolean templateIsProcessedEducation = latexService.processTemplate(templatePatheducation, targetEducation,
				educationInfo);

		if (templateIsProcessedMain && templateIsProcessedExperience && templateIsProcessedEducation) {
			System.out.println("Template processed successfully!");
		} else {
			if (!templateIsProcessedMain) {
				System.out.println("CV generation failed: Main template processing failed!");
			}
			if (!templateIsProcessedExperience) {
				System.out.println("CV generation failed: Experience template processing failed!");
			}
			if (!templateIsProcessedEducation) {
				System.out.println("CV generation failed: Education template processing failed!");
			}
		}
	}
}