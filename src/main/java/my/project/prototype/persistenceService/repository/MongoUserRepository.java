package my.project.prototype.persistenceService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import my.project.prototype.persistenceService.entities.MongoUser;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
}