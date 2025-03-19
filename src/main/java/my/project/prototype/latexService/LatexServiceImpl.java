package my.project.prototype.latexService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class LatexServiceImpl implements LatexServiceInterface {

	@Override
	public boolean processTemplate(String templatePath, String fileOutPutPath, Map<String, String> values) {
		StringBuilder content = new StringBuilder();
		Pattern pattern = Pattern.compile("<(.*?)>");

		try (BufferedReader reader = new BufferedReader(new FileReader(templatePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					String key = matcher.group(1);
					if (values.containsKey(key)) {
						line = line.replace("<" + key + ">", values.get(key));
					}
				}
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println("Error reading template: " + e.getMessage());
			return false;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutPutPath))) {
			writer.write(content.toString());
			System.out.println("File updated successfully!");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public File compileLatex(File latexFile) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("pdflatex", latexFile.getAbsolutePath());
			processBuilder.directory(latexFile.getParentFile());
			Process process = processBuilder.start();
			process.waitFor();

			String pdfFilePath = latexFile.getAbsolutePath().replace(".tex", ".pdf");
			return new File(pdfFilePath);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException("Failed to compile LaTeX file", e);
		}
	}
}