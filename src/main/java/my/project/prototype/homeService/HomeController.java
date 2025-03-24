package my.project.prototype.homeService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import my.project.prototype.models.User;

@Controller
public class HomeController implements HomeControllerInterface {

	@Autowired
	private HomeServiceInterface homeService;

	@Override
	public void generateCV(User user) throws IOException {
		homeService.generateCV(user);
	}
}