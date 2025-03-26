package my.project.prototype.latexService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class LatexServiceImplTest {

	@InjectMocks
	private LatexServiceImpl latexService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testProcessTemplateSuccess() throws IOException {
		// Create a temporary template file
		File templateFile = File.createTempFile("template", ".txt");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(templateFile))) {
			writer.write("Hello, <name>!");
		}

		// Create a temporary output file
		File outputFile = File.createTempFile("output", ".txt");
		String outputFilePath = outputFile.getAbsolutePath().replace(".txt", ".tex");

		Map<String, String> values = new HashMap<>();
		values.put("name", "John Doe");

		boolean result = latexService.processTemplate(templateFile.getAbsolutePath(), outputFilePath, values);

		assertTrue(result);

		// Verify the content of the output file
		try (BufferedReader reader = new BufferedReader(new FileReader(outputFilePath))) {
			String line = reader.readLine();
			assertTrue(line.equals("Hello, John Doe!"));
		}

		// Clean up temporary files
		templateFile.delete();
		new File(outputFilePath).delete();
	}

	@Test
	void testProcessTemplateReadError() {
		String templatePath = "invalid/path/template.txt";
		String fileOutPutPath = "src/test/resources/output.tex";
		Map<String, String> values = new HashMap<>();

		boolean result = latexService.processTemplate(templatePath, fileOutPutPath, values);

		assertFalse(result);
	}

	@Test
	void testProcessTemplateWriteError() throws IOException {
		// Create a temporary template file
		File templateFile = File.createTempFile("template", ".txt");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(templateFile))) {
			writer.write("Hello, <name>!");
		}

		String fileOutPutPath = "invalid/path/output.tex";
		Map<String, String> values = new HashMap<>();
		values.put("name", "John Doe");

		boolean result = latexService.processTemplate(templateFile.getAbsolutePath(), fileOutPutPath, values);

		assertFalse(result);

		// Clean up temporary file
		templateFile.delete();
	}
}