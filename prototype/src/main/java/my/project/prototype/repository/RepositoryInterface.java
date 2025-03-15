package my.project.prototype.repository;

import my.project.prototype.models.User;

public interface RepositoryInterface {
    User save(User user);
    User load(User user);
}