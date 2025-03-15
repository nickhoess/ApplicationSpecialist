package my.project.prototype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import my.project.prototype.controller.HomeController;
import my.project.prototype.models.User;

@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private HomeController homecontroller;
    
    private User user;

    public static void main(String[] args) {
        SpringApplication.run(PrototypeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && "no-shell".equals(args[0])) {
            return;
        }

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
                    case "hello" -> System.out.println("Hello, World!");
                    case "set-user" -> setUser(scanner);
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

    private void setUser(Scanner scanner) {
        user = new User();
        System.out.print("Enter name: ");
        user.setName(scanner.nextLine());
        System.out.print("Enter email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        user.setPhone(scanner.nextLine());
        System.out.print("Enter address: ");
        user.setAddress(scanner.nextLine());

        // Add more interactive inputs for skills, work experiences, education, and projects as needed
        // For simplicity, we'll use empty lists here
        user.setSkills(new ArrayList<>());
        user.setWorkExperiences(new ArrayList<>());
        user.setEducation(new ArrayList<>());
        user.setProjects(new ArrayList<>());
        System.out.println("User information set.");
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
        System.out.println("  hello       - Print 'Hello, World!'");
        System.out.println("  set-user    - Set user information");
        System.out.println("  show-user   - Show current user information");
        System.out.println("  generate-cv - Generate CV in LaTeX format");
        System.out.println("  help        - Show this help message");
        System.out.println("  exit        - Exit the shell");
    }
}