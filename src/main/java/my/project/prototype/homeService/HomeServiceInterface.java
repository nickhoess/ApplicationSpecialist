package my.project.prototype.homeService;

import java.io.IOException;

import my.project.prototype.models.User;

public interface HomeServiceInterface {
	void generateCV(User user) throws IOException;
}