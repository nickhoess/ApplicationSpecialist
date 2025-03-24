package my.project.prototype.persistenceService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import my.project.prototype.models.User;
import my.project.prototype.persistenceService.entities.MongoUser;
import my.project.prototype.persistenceService.repository.MongoUserRepository;

public class PersistenceServiceImplTest {

	@Mock
	private MongoUserRepository mongoUserRepository;

	@InjectMocks
	private PersistenceServiceImpl persistenceService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveUser() {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPhone("1234567890");
		user.setAddress("123 Main St");

		when(mongoUserRepository.save(any(MongoUser.class))).thenReturn(new MongoUser());

		boolean result = persistenceService.saveUser(user);

		assertEquals(true, result);
	}

	@Test
	void testGetUserByID() {
		String userId = "12345";
		MongoUser mongoUser = new MongoUser();
		mongoUser.setName("John Doe");
		mongoUser.setEmail("john.doe@example.com");
		mongoUser.setPhone("1234567890");
		mongoUser.setAddress("123 Main St");

		when(mongoUserRepository.findById(userId)).thenReturn(Optional.of(mongoUser));

		User user = persistenceService.getUserbyID(userId);

		assertNotNull(user);
		assertEquals("John Doe", user.getName());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("1234567890", user.getPhone());
		assertEquals("123 Main St", user.getAddress());
	}

	@Test
	void testGetUserByIDNotFound() {
		String userId = "12345";

		when(mongoUserRepository.findById(userId)).thenReturn(Optional.empty());

		User user = persistenceService.getUserbyID(userId);

		assertNull(user);
	}
}