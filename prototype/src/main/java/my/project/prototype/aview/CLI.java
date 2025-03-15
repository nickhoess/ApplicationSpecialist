package my.project.prototype.aview;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.project.prototype.controller.HomeController;
import my.project.prototype.models.User;

@Component
public class CLI {

    @Autowired
    private HomeController homecontroller;
    
    private User user;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Simple Shell. Type 'exit' to quit.");

        try {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                command = scanner.nextLine();

                if ("exit".equalsIgnoreCase(command)) {
                    System.out.println("Exiting shell...");
                    System.exit(0);
                }

                // Handle commands here
                switch (command.toLowerCase()) {
                    case "hello" -> {
                        System.out.println("Hello, There!");
                        System.out.println("----------------!General Kenobi");
                    }
                    case "set-user" -> readPersonalInfo(scanner);
                    case "show-user" -> showUser();
                    case "generate-cv" -> generateCV();
                    case "help" -> showHelp();
                    default -> System.out.println("Unknown command: " + command);
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void generateCV() {
        try {
            if (user != null) {
                homecontroller.generateCV(user);
            }
        } catch (IOException e) {
            System.out.println("Failed to generate CV: " + e.getMessage());
        }
    }

    private User readPersonalInfo(Scanner scanner) {
        user = new User();
        System.out.print("Enter name: ");
        user.setName(scanner.nextLine());
        System.out.print("Enter email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        user.setPhone(scanner.nextLine());
        System.out.print("Enter address: ");
        user.setAddress(scanner.nextLine());

        // Prompt for paths to JSON files for additional information
        System.out.print("Enter path to JSON file for skills: ");
        String skillsJsonFilePath = scanner.nextLine();
        System.out.println("Skills JSON file path: " + skillsJsonFilePath);

        System.out.print("Enter path to JSON file for work experiences: ");
        String workExperiencesJsonFilePath = scanner.nextLine();
        System.out.println("Work experiences JSON file path: " + workExperiencesJsonFilePath);

        System.out.print("Enter path to JSON file for education: ");
        String educationJsonFilePath = scanner.nextLine();
        System.out.println("Education JSON file path: " + educationJsonFilePath);

        System.out.print("Enter path to JSON file for projects: ");
        String projectsJsonFilePath = scanner.nextLine();
        System.out.println("Projects JSON file path: " + projectsJsonFilePath);

        System.out.println("User information set.");
        
        return user;
    }

    private void showUser() {
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("No user information available.");
        }
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("  hello       - Print 'Hello, There!' and 'General Kenobi!'");
        System.out.println("  set-user    - Set user information");
        System.out.println("  show-user   - Show current user information");
        System.out.println("  generate-cv - Generate CV in LaTeX format");
        System.out.println("  help        - Show this help message");
        System.out.println("  exit        - Exit the shell");
    }
}