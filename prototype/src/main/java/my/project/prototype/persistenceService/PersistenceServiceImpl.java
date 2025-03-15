// filepath: c:\ApplicationSpecialist\prototype\src\main\java\my\project\prototype\persistenceService\PersistenceServiceImpl.java
package my.project.prototype.persistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.project.prototype.models.User;
import my.project.prototype.persistenceService.entities.MongoUser;
import my.project.prototype.persistenceService.repository.MongoUserRepository;

@Service
public class PersistenceServiceImpl {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    public User saveData(User user) {
        MongoUser mongoUser = new MongoUser();
        mongoUserRepository.save(mongoUser);
        return user;
    }

    public boolean saveUser(User user) {
        MongoUser mongoUser = transformToMongoUser(user);
        mongoUserRepository.save(mongoUser);
        return true;
    }

    private MongoUser transformToMongoUser(User user) {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName(user.getName());
        mongoUser.setEmail(user.getEmail());
        mongoUser.setPhone(user.getPhone());
        mongoUser.setAddress(user.getAddress());
        mongoUser.setSkills(user.getSkills());
        mongoUser.setWorkExperiences(user.getWorkExperiences());
        mongoUser.setEducation(user.getEducation());
        mongoUser.setProjects(user.getProjects());
        return mongoUser;
    }
}