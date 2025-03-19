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
		String fileoutputpath = fileOutPutPath;
		Pattern pattern = Pattern.compile("<(.*?)>");
		System.out.println("matcher key5");
		// Read file and replace <dummy> placeholders
		try (BufferedReader reader = new BufferedReader(new FileReader(templatePath))) {
			String line;
			System.out.println("matcher key4");
			while ((line = reader.readLine()) != null) {
				System.out.println("matcher key2");
				Matcher matcher = pattern.matcher(line);
				System.out.println("matcher key1");
				if (matcher.find()) {
					String key = matcher.group(1);
					if (!values.containsKey(key)) {
						System.out.println("Key not found: " + key);
						continue;
					}
					System.out.println(key);
					line = line.replace("<" + key + ">", values.get(key));
				}
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println("latex service ioe");
			return false;
		}

		// Write updated content back to file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileoutputpath))) {
			writer.write(content.toString());
			System.out.println("File updated successfully!");
		} catch (IOException e) {
			System.out.println("latex service ioe2");
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