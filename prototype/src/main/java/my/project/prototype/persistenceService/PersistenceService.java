package my.project.prototype.persistenceService;

import my.project.prototype.models.User;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService implements PersistenceServiceInterface {

    @Override
    public void save(User user) {
        // Save user to database
    }

    @Override
    public void load(User user) {
        // Load user from database
    }
}
