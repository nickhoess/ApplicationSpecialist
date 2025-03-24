package my.project.prototype.homeService;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import my.project.prototype.models.User;

public class HomeControllerTest {

	@Mock
	private HomeServiceInterface homeService;

	@InjectMocks
	private HomeController homeController;

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

		homeController.generateCV(user);

		verify(homeService, times(1)).generateCV(user);
	}
}