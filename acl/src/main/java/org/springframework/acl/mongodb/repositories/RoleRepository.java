package org.springframework.acl.mongodb.repositories;

import org.springframework.acl.mongodb.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
}