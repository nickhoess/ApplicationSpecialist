package my.project.prototype.dataService;

import my.project.prototype.models.User;
import my.project.prototype.models.WorkExperience;
import my.project.prototype.models.Education;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.project.prototype.models.TechSkill;

public interface DataServiceInterface {
	/**
	 * Reads work experience data from a JSON file.
	 * 
	 * @param filePath
	 *            Path to the JSON file.
	 * @return List of WorkExperience objects.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	public List<TechSkill> readTechnicalSkillsJsonData(String filePath) throws IOException;

	/**
	 * Reads work experience data from a JSON file.
	 * 
	 * @param filePath
	 *            Path to the JSON file.
	 * @return List of WorkExperience objects.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	List<WorkExperience> readExperienceJsonData(String filePath) throws IOException;

	/**
	 * Reads education data from a JSON file.
	 * 
	 * @param filePath
	 *            Path to the JSON file.
	 * @return List of Education objects.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	List<Education> readEducationJsonData(String filePath) throws IOException;

	/**
	 * Builds a map of user details.
	 * 
	 * @param user
	 *            The User object.
	 * @return Map of user details.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	Map<String, String> buildMapper(User user) throws IOException;

	/**
	 * Builds a map of user work experiences.
	 * 
	 * @param user
	 *            The User object.
	 * @return Map of user work experiences.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	Map<String, String> buildExperienceMapper(User user) throws IOException;

	/**
	 * Builds a map of user education details.
	 * 
	 * @param user
	 *            The User object.
	 * @return Map of user education details.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	Map<String, String> buildEducationMapper(User user) throws IOException;

	/**
	 * Builds a map of user education details.
	 * 
	 * @param user
	 *            The User object.
	 * @return Map of user education details.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */

	public Map<String, String> buildTechnicalSkillsMapper(User user) throws IOException;
}