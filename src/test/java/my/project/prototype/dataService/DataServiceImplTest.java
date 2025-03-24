package my.project.prototype.dataService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import my.project.prototype.models.Education;
import my.project.prototype.models.User;
import my.project.prototype.models.WorkExperience;

class DataServiceImplTest {

	private DataServiceImpl dataService;

	@BeforeEach
	@SuppressWarnings("unused")
	void setUp() {
		dataService = new DataServiceImpl();
	}

	@Test
	void testReadExperienceJsonData() throws IOException {
		Resource resource = new ClassPathResource("experience.json");
		List<WorkExperience> experiences = dataService.readExperienceJsonData(resource.getFile().getAbsolutePath());

		assertNotNull(experiences);
		assertEquals(1, experiences.size());

		WorkExperience experience = experiences.get(0);
		assertEquals("Example Corp", experience.getCompanyName());
		assertEquals("Software Engineer", experience.getJobTitle());
		assertEquals("2020-01-01", experience.getStartDate());
		assertEquals("2023-01-01", experience.getEndDate());
		assertEquals("Software Development", experience.getSpecialization());
		assertEquals("New York, NY", experience.getLocation());
		assertEquals(2, experience.getExperienceItems().size());
	}

	@Test
	void testReadEducationJsonData() throws IOException {
		Resource resource = new ClassPathResource("education.json");
		List<Education> educationList = dataService.readEducationJsonData(resource.getFile().getAbsolutePath());

		assertNotNull(educationList);
		assertEquals(1, educationList.size());

		Education education = educationList.get(0);
		assertEquals("B.Sc. Computer Science", education.getDegree());
		assertEquals("University of Example", education.getInstitution());
		assertEquals("2016-09-01", education.getStartDate());
		assertEquals("2020-06-01", education.getEndDate());
		assertEquals("Example City", education.getLocation());
		assertEquals(2, education.getEducationItems().size());
	}

	@Test
	void testBuildMapper() throws IOException {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPhone("1234567890");
		user.setAddress("123 Main St");

		Map<String, String> map = dataService.buildMapper(user);

		assertNotNull(map);
		assertEquals(4, map.size());
		assertEquals("John Doe", map.get("name"));
		assertEquals("john.doe@example.com", map.get("email"));
		assertEquals("1234567890", map.get("phone"));
		assertEquals("123 Main St", map.get("address"));
	}
}