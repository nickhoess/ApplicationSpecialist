package my.project.prototype.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.project.prototype.latexService.LatexServiceImpl;
import my.project.prototype.latexService.LatexServiceInterface;
import my.project.prototype.models.User;
import my.project.prototype.persistenceService.PersistenceServiceImpl;

@Service
public class HomeController {

    private final LatexServiceInterface latexService;
    @Autowired
    private PersistenceServiceImpl persistenceService;
    
        public HomeController() {
            this.latexService = new LatexServiceImpl();
    }

    public void generateCV(User user) throws IOException {

        //save to DB
        if(!persistenceService.saveUser(user)) {
            System.out.println("Cant save user , check persitence service");
        }
        // Prepare the values map
        Map<String, String> values = new HashMap<>();

        //Todo: Fetch user data from the database
        // For now, we'll use some dummy data
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());


        // Delegate the processing of the template and compilation to the LatexService
        String templatePath = "C:\\ApplicationSpecialist\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt";
        boolean templateIsProcessed = latexService.processTemplate(templatePath, values);
        if (templateIsProcessed) {
            System.out.println("Template processed successfully!");
            return;
        }
        System.out.println("CV generated Failed!");
    }
}