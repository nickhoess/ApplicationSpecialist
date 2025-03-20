package my.project.prototype.persistenceService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.project.prototype.models.User;
import my.project.prototype.persistenceService.entities.MongoUser;
import my.project.prototype.persistenceService.repository.MongoUserRepository;

@Service
public class PersistenceServiceImpl implements PersistenceServiceInterface {

	@Autowired
	private MongoUserRepository mongoUserRepository;

	@Override
	public User saveData(User user) {
		MongoUser mongoUser = transformToMongoUser(user);
		mongoUserRepository.save(mongoUser);
		return user;
	}

	@Override
	public boolean saveUser(User user) {
		MongoUser mongoUser = transformToMongoUser(user);
		mongoUserRepository.save(mongoUser);
		return true;
	}

	@Override
	public User getUserbyID(String id) {
		Optional<MongoUser> optionalMongoUser = mongoUserRepository.findById(id);
		if (optionalMongoUser.isPresent()) {
			MongoUser mongoUser = optionalMongoUser.get();
			return transformToUser(mongoUser);
		} else {
			return null; // or throw an exception if preferred
		}
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
		mongoUser.setSkillsJsonFilePath(user.getSkillsJsonFilePath());
		mongoUser.setWorkExperiencesJsonFilePath(user.getWorkExperiencesJsonFilePath());
		mongoUser.setEducationJsonFilePath(user.getEducationJsonFilePath());
		mongoUser.setProjectsJsonFilePath(user.getProjectsJsonFilePath());
		return mongoUser;
	}

	private User transformToUser(MongoUser mongoUser) {
		User user = new User();
		user.setName(mongoUser.getName());
		user.setEmail(mongoUser.getEmail());
		user.setPhone(mongoUser.getPhone());
		user.setAddress(mongoUser.getAddress());
		user.setSkills(mongoUser.getSkills());
		user.setWorkExperiences(mongoUser.getWorkExperiences());
		user.setEducation(mongoUser.getEducation());
		user.setProjects(mongoUser.getProjects());
		user.setSkillsJsonFilePath(mongoUser.getSkillsJsonFilePath());
		user.setWorkExperiencesJsonFilePath(mongoUser.getWorkExperiencesJsonFilePath());
		user.setEducationJsonFilePath(mongoUser.getEducationJsonFilePath());
		user.setProjectsJsonFilePath(mongoUser.getProjectsJsonFilePath());
		return user;
	}
}