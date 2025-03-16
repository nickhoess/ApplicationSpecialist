package my.project.prototype.latexService;

import java.io.File;
import java.util.Map;

public interface LatexServiceInterface {
    
    /**
     * Replaces placeholders in the LaTeX template with actual values.
     * @param templatePath Path to the LaTeX template file.
     * @param values Map of placeholder keys and their corresponding values.
     * @return A new LaTeX file with values replaced.
     */
    boolean processTemplate(String templatePath, Map<String, String> values);

    /**
     * Compiles a LaTeX file to a PDF.
     * @param latexFile The processed LaTeX file.
     * @return The generated PDF file.
     */
    File compileLatex(File latexFile);
}
