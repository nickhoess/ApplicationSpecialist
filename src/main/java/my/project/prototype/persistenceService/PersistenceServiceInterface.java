package my.project.prototype.persistenceService;
import my.project.prototype.models.User;

public interface PersistenceServiceInterface {

	void save(User user);

	void load(User user);
}