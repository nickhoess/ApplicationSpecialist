package my.project.prototype.homeService;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import my.project.prototype.dataService.DataServiceInterface;
import my.project.prototype.latexService.LatexServiceInterface;
import my.project.prototype.models.User;
import my.project.prototype.persistenceService.PersistenceServiceInterface;

public class HomeServiceImplTest {

	@Mock
	private PersistenceServiceInterface persistenceService;

	@Mock
	private DataServiceInterface dataService;

	@Mock
	private LatexServiceInterface latexService;

	@InjectMocks
	private HomeServiceImpl homeService;

	@BeforeEach
	@SuppressWarnings("unused")
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGenerateCV() throws IOException {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPhone("1234567890");
		user.setAddress("123 Main St");

		when(dataService
				.readEducationJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\education.json"))
						.thenReturn(null);
		when(dataService
				.readExperienceJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\experience.json"))
						.thenReturn(null);
		when(persistenceService.saveUser(user)).thenReturn(true);
		when(dataService.buildMapper(user)).thenReturn(new HashMap<>());
		when(dataService.buildExperienceMapper(user)).thenReturn(new HashMap<>());
		when(dataService.buildEducationMapper(user)).thenReturn(new HashMap<>());
		when(latexService.processTemplate("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt",
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main_finalized.txt", new HashMap<>()))
						.thenReturn(true);
		when(latexService.processTemplate(
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\experience.txt",
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\worksexperience_finalized.txt",
				new HashMap<>())).thenReturn(true);
		when(latexService.processTemplate(
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\education.txt",
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\education_finalized.txt",
				new HashMap<>())).thenReturn(true);

		homeService.generateCV(user);

		verify(dataService, times(1))
				.readEducationJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\education.json");
		verify(dataService, times(1))
				.readExperienceJsonData("C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\json\\experience.json");
		verify(persistenceService, times(1)).saveUser(user);
		verify(dataService, times(1)).buildMapper(user);
		verify(dataService, times(1)).buildExperienceMapper(user);
		verify(dataService, times(1)).buildEducationMapper(user);
		verify(latexService, times(1)).processTemplate(
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main.txt",
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\main_finalized.txt", new HashMap<>());
		verify(latexService, times(1)).processTemplate(
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\experience.txt",
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\worksexperience_finalized.txt",
				new HashMap<>());
		verify(latexService, times(1)).processTemplate(
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\cv-sections\\education.txt",
				"C:\\vsc\\ApplicationSpecialist\\src\\main\\resources\\textext\\education_finalized.txt",
				new HashMap<>());
	}
}