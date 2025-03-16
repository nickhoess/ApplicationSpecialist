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
		// Save to DB
		if (!persistenceService.saveUser(user)) {
			System.out.println("Can't save user, check persistence service");
			return;
		}

		// Delegate the processing of the template and compilation to the LatexService
		String templatePath = "C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt";
		Map<String, String> values = dataService.buildMapper(user);
		boolean templateIsProcessed = latexService.processTemplate(templatePath, values);

		if (templateIsProcessed) {
			System.out.println("Template processed successfully!");
		} else {
			System.out.println("CV generation failed!");
		}
	}
}