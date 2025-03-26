package my.project.prototype.latexService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

		if (!fileOutPutPath.endsWith(".tex")) {
			fileOutPutPath = fileOutPutPath.replaceAll("\\.\\w+$", "") + ".tex";
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
			System.out.println("Starting LaTeX compilation for file: " + latexFile.getAbsolutePath());

			// Use xelatex instead of pdflatex
			ProcessBuilder processBuilder = new ProcessBuilder("xelatex", latexFile.getAbsolutePath());
			processBuilder.directory(latexFile.getParentFile());
			processBuilder.redirectErrorStream(true); // Combine stdout and stderr
			Process process = processBuilder.start();

			// Capture and log the output of the process
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}

			System.out.println("LaTeX compilation process started, waiting for it to complete...");
			int exitCode = process.waitFor();
			System.out.println("LaTeX compilation process completed with exit code: " + exitCode);

			if (exitCode != 0) {
				System.out.println("LaTeX compilation failed with exit code: " + exitCode);
			}

			String pdfFilePath = latexFile.getAbsolutePath().replace(".tex", ".pdf");
			File pdfFile = new File(pdfFilePath);

			if (pdfFile.exists()) {
				System.out.println("PDF generated successfully: " + pdfFilePath);
			} else {
				System.out.println("PDF generation failed.");
			}

			return pdfFile;
		} catch (IOException e) {
			System.out.println("IOException occurred during LaTeX compilation: " + e.getMessage());
			throw new RuntimeException("Failed to compile LaTeX file", e);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException occurred during LaTeX compilation: " + e.getMessage());
			throw new RuntimeException("Failed to compile LaTeX file", e);
		}
	}
}