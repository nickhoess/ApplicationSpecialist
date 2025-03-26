package my.project.prototype.dataService;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import my.project.prototype.models.Education;
import my.project.prototype.models.TechSkill;
import my.project.prototype.models.User;
import my.project.prototype.models.WorkExperience;

@Service
public class DataServiceImpl implements DataServiceInterface {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<TechSkill> readTechnicalSkillsJsonData(String filePath) throws IOException {
		return objectMapper.readValue(new FileReader(filePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, TechSkill.class));
	}

	@Override
	public List<WorkExperience> readExperienceJsonData(String filePath) throws IOException {
		return objectMapper.readValue(new FileReader(filePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, WorkExperience.class));
	}

	@Override
	public List<Education> readEducationJsonData(String filePath) throws IOException {
		return objectMapper.readValue(new FileReader(filePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, Education.class));
	}

	@Override
	public Map<String, String> buildMapper(User user) throws IOException {
		Map<String, String> map = new HashMap<>();

		if (user != null) {
			System.out.println("User object: " + user.toString());

			if (user.getName() != null) {
				System.out.println("Name: " + user.getName());
				map.put("name", user.getName());
			} else {
				System.out.println("Name is null");
			}

			if (user.getEmail() != null) {
				System.out.println("Email: " + user.getEmail());
				map.put("email", user.getEmail());
			} else {
				System.out.println("Email is null");
			}

			if (user.getPhone() != null) {
				System.out.println("Phone: " + user.getPhone());
				map.put("phone", user.getPhone());
			} else {
				System.out.println("Phone is null");
			}

			if (user.getAddress() != null) {
				System.out.println("Address: " + user.getAddress());
				map.put("address", user.getAddress());
			} else {
				System.out.println("Address is null");
			}
		}

		System.out.println("Map: " + map.toString());
		return map;
	}

	@Override
	public Map<String, String> buildExperienceMapper(User user) throws IOException {
		Map<String, String> map = new HashMap<>();

		if (user.getWorkExperiences() != null) {
			for (int i = 0; i < user.getWorkExperiences().size(); i++) {
				WorkExperience experience = user.getWorkExperiences().get(i);
				map.put("jobTitle" + (i + 1), experience.getJobTitle());
				map.put("companyName" + (i + 1), experience.getCompanyName());
				map.put("location" + (i + 1), experience.getLocation());
				map.put("startDate" + (i + 1), experience.getStartDate());
				map.put("endDate" + (i + 1), experience.getEndDate());
				map.put("experienceItems" + (i + 1), String.join("\n", experience.getExperienceItems()));
			}
		}

		System.out.println("Experience Map: " + map.toString());
		return map;
	}

	@Override
	public Map<String, String> buildEducationMapper(User user) throws IOException {
		Map<String, String> map = new HashMap<>();

		if (user.getEducation() != null) {
			for (int i = 0; i < user.getEducation().size(); i++) {
				Education education = user.getEducation().get(i);
				map.put("degree" + (i + 1), education.getDegree());
				map.put("institution" + (i + 1), education.getInstitution());
				map.put("location" + (i + 1), education.getLocation());
				map.put("startDate" + (i + 1), education.getStartDate());
				map.put("endDate" + (i + 1), education.getEndDate());
				map.put("educationItems" + (i + 1), String.join("\n", education.getEducationItems()));
			}
		}

		System.out.println("Education Map: " + map.toString());
		return map;
	}

	@Override
	public Map<String, String> buildTechnicalSkillsMapper(User user) throws IOException {
		Map<String, String> map = new HashMap<>();

		if (user.getSkills() != null) {
			for (int i = 0; i < user.getSkills().size(); i++) {
				TechSkill skill = user.getSkills().get(i);
				map.put("category" + (i + 1), skill.getCategory());
				map.put("skills" + (i + 1), String.join(", ", skill.getSkills()));
			}
		}

		System.out.println("Technical Skills Map: " + map.toString());
		return map;
	}
}