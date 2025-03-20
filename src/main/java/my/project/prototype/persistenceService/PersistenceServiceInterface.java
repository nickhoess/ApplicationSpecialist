package my.project.prototype.persistenceService;

import my.project.prototype.models.User;

public interface PersistenceServiceInterface {

	/**
	 * Saves the user data.
	 * 
	 * @param user
	 *            The User object to be saved.
	 * @return The saved User object.
	 */
	User saveData(User user);

	/**
	 * Saves the user.
	 * 
	 * @param user
	 *            The User object to be saved.
	 * @return true if the user was saved successfully, false otherwise.
	 */
	boolean saveUser(User user);

	/**
	 * Retrieves a user by their ID.
	 * 
	 * @param id
	 *            The ID of the user to retrieve.
	 * @return The User object if found, null otherwise.
	 */
	User getUserbyID(String id);
}