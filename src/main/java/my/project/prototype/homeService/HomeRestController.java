package my.project.prototype.homeService;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.project.prototype.models.User;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*") // All
public class HomeRestController {

	private static final Logger logger = LoggerFactory.getLogger(HomeRestController.class);

	@Autowired
	private HomeServiceInterface homeService;

	@PostMapping("/generateCV")
	public ResponseEntity<String> generateCV(@RequestBody User user) {
		logger.info("Received request to generate CV for user: {}", user);
		try {
			homeService.generateCV(user);
			return ResponseEntity.ok("CV generated successfully!");
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Error generating CV: " + e.getMessage());
		}
	}
}