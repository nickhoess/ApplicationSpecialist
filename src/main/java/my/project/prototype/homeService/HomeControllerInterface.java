package my.project.prototype.homeService;

import java.io.IOException;

import my.project.prototype.models.User;

public interface HomeControllerInterface {

	/**
	 * Generates a CV for the given user.
	 * 
	 * @param user
	 *            The User object.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	void generateCV(User user) throws IOException;

	// Other methods can be added here if needed
}