package my.project.prototype.homeService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.project.prototype.models.User;

@RestController
@RequestMapping("/api/home")
public class HomeRestController {

	@Autowired
	private HomeServiceInterface homeService;

	@PostMapping("/generateCV")
	public ResponseEntity<String> generateCV(@RequestBody User user) {
		try {
			homeService.generateCV(user);
			return ResponseEntity.ok("CV generated successfully!");
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Error generating CV: " + e.getMessage());
		}
	}
}