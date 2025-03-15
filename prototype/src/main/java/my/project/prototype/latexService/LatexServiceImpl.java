package my.project.prototype.latexService;

import java.io.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexServiceImpl implements LatexServiceInterface {

    @Override
    public boolean processTemplate(String templatePath, Map<String, String> values) {
        
        StringBuilder content = new StringBuilder();
        String fileoutputpath = "C:\\ApplicationSpecialist\\prototype\\src\\main\\resources\\textext\\main_finalized.txt";
        Pattern pattern = Pattern.compile("<(.*?)>");

        // Read file and replace <dummy> placeholders
        try (BufferedReader reader = new BufferedReader(new FileReader(templatePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    String key = matcher.group(1);
                    if (!values.containsKey(key)) {
                        System.out.println("Key not found: " + key);
                        //map not complete yet
                        break;
                    }
                    line = line.replace("<" + key + ">", values.get(key));
                }
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Write updated content back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileoutputpath))) {
            writer.write(content.toString());
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public File compileLatex(File latexFile) {
        try {
            // Compile the LaTeX file to PDF using an external tool like pdflatex
            ProcessBuilder processBuilder = new ProcessBuilder("pdflatex", latexFile.getAbsolutePath());
            processBuilder.directory(latexFile.getParentFile());
            Process process = processBuilder.start();
            process.waitFor();

            // Return the generated PDF file
            String pdfFilePath = latexFile.getAbsolutePath().replace(".tex", ".pdf");
            return new File(pdfFilePath);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to compile LaTeX file", e);
        }
    }
}