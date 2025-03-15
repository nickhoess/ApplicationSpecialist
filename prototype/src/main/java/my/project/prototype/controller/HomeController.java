package my.project.prototype.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.project.prototype.dataService.DataServiceImpl;
import my.project.prototype.latexService.LatexServiceImpl;
import my.project.prototype.latexService.LatexServiceInterface;
import my.project.prototype.models.User;
import my.project.prototype.persistenceService.PersistenceServiceImpl;

@Service
public class HomeController {

    @Autowired
    private PersistenceServiceImpl persistenceService;
    @Autowired
    private DataServiceImpl dataService;
    @Autowired
    private final LatexServiceInterface latexService;

    public HomeController() {
        this.latexService = new LatexServiceImpl();
    }

    public void generateCV(User user) throws IOException {
        // Save to DB
        if (!persistenceService.saveUser(user)) {
            System.out.println("Can't save user, check persistence service");
        }
        // Delegate the processing of the template and compilation to the LatexService
        String templatePath = "C:\\ApplicationSpecialist\\prototype\\src\\main\\resources\\textext\\main.txt";
        boolean templateIsProcessed = latexService.processTemplate(templatePath, buildMapper(user));
        if (templateIsProcessed) {
            System.out.println("Template processed successfully!");
            return;
        }
        System.out.println("CV generation failed!");
    }

    public Map<String, String> buildMapper(User user) throws IOException {
        Map<String, String> map = dataService.buildMapper(user);
        return map;
    }
}