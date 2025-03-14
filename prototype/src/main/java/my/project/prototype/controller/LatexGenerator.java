package my.project.prototype.controller;

import my.project.prototype.models.LatexDocument;
import my.project.prototype.models.WorkExperience;
import my.project.prototype.models.SoftwareProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LatexGenerator {

    public void generateCV() {
        try {
            // Use an explicit path to the template file
            String templatePath = "C:/ApplicationSpecialist/prototype/src/main/resources/template.txt";
            System.out.println("Reading template from: " + templatePath);

            // Read the template file using FileReader and BufferedReader
            StringBuilder templateBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(templatePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    templateBuilder.append(line).append("\n");
                }
            }
            String template = templateBuilder.toString();
            System.out.println("Template content read successfully.");
            System.out.println("Template content before writing:");
            System.out.println(template);

            // Hardcoded user information
            LatexDocument document = new LatexDocument();
            document.setName("John Doe");
            document.setAddress("123 Main St, Anytown, USA");
            document.setPhone("(123) 456-7890");
            document.setEmail("john.doe@example.com");
            document.setPosition("Software Engineer");
            document.setQuote("Make the change that you want to see in the world.");
            document.setEducation(Arrays.asList("B.Sc. in Computer Science, Anytown University, 2020"));
            document.setSkills(Arrays.asList("Java", "Spring Boot", "SQL", "Git", "Docker"));
            
            WorkExperience experience = new WorkExperience();
            experience.setJobTitle("Software Developer");
            experience.setCompanyName("Tech Corp");
            experience.setStartDate("January 2021");
            experience.setEndDate("Present");
            SoftwareProject project = new SoftwareProject();
            project.setName("Project Alpha");
            project.setDescription("Developed a web application using Spring Boot and React.");
            experience.setProjects(Arrays.asList(project));
            document.setWorkExperiences(Arrays.asList(experience));

            template = template.replace("<name>", document.getName())
                               .replace("<address>", document.getAddress())
                               .replace("<phone>", document.getPhone())
                               .replace("<email>", document.getEmail())
                               .replace("<position>", document.getPosition())
                               .replace("<quote>", document.getQuote())
                               .replace("<education>", formatList(document.getEducation()))
                               .replace("<skills>", formatList(document.getSkills()))
                               .replace("<experience>", formatExperience(document.getWorkExperiences()));
            System.out.println("Template placeholders replaced successfully.");
            System.out.println(template);

            String outputPath = "C:/ApplicationSpecialist/prototype/src/main/java/my/project/prototype/controller/resume.txt";
            System.out.println("Writing CV to: " + outputPath);

            // Write the output file using FileWriter and BufferedWriter
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write(template);
                System.out.println("CV generated successfully.");
            }
        } catch (IOException e) {
            System.out.println("Failed to generate CV: " + e.getMessage());
        }
    }

    private String formatList(List<String> items) {
        return items.stream()
                    .map(item -> "\\item " + item)
                    .collect(Collectors.joining("\n"));
    }

    private String formatExperience(List<WorkExperience> experiences) {
        StringBuilder experienceBuilder = new StringBuilder();
        for (WorkExperience experience : experiences) {
            experienceBuilder.append("\\item ")
                             .append(experience.getJobTitle())
                             .append(" at ")
                             .append(experience.getCompanyName())
                             .append(" (")
                             .append(experience.getStartDate())
                             .append(" - ")
                             .append(experience.getEndDate())
                             .append(")\n")
                             .append("\\begin{itemize}\n");
            for (SoftwareProject project : experience.getProjects()) {
                experienceBuilder.append("\\item ")
                                 .append(project.getName())
                                 .append(": ")
                                 .append(project.getDescription())
                                 .append("\n");
            }
            experienceBuilder.append("\\end{itemize}\n");
        }
        return experienceBuilder.toString();
    }
}