package org.springframework.acl.mongodb.repositories;

import org.springframework.acl.mongodb.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}