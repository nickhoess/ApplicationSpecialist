package my.project.prototype.homeService;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import my.project.prototype.models.User;

public class HomeRestControllerTest {

	@Mock
	private HomeServiceInterface homeService;

	@InjectMocks
	private HomeRestController homeRestController;

	@BeforeEach
	@SuppressWarnings("unused")
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@SuppressWarnings("null")
	@Test
	void testGenerateCV() throws IOException {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPhone("1234567890");
		user.setAddress("123 Main St");

		// Stub the generateCV method to do nothing (void method)
		doNothing().when(homeService).generateCV(user);

		ResponseEntity<String> response = homeRestController.generateCV(user);

		verify(homeService, times(1)).generateCV(user);
		assert (response.getStatusCode() == HttpStatus.OK);
		assert (response.getBody() != null && response.getBody().equals("CV generated successfully!"));
	}

	@SuppressWarnings("null")
	@Test
	void testGenerateCVWithException() throws IOException {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPhone("1234567890");
		user.setAddress("123 Main St");

		// Stub the generateCV method to throw an IOException
		doThrow(new IOException("Error generating CV")).when(homeService).generateCV(user);

		ResponseEntity<String> response = homeRestController.generateCV(user);

		verify(homeService, times(1)).generateCV(user);
		assert (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
		assert (response.getBody() != null
				&& response.getBody().equals("Error generating CV. Please try again later."));
	}
}