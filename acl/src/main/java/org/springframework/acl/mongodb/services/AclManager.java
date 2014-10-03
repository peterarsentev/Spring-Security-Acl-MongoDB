package org.springframework.acl.mongodb.services;

import org.springframework.acl.mongodb.models.EntityClass;
import org.springframework.acl.mongodb.models.Role;
import org.springframework.acl.mongodb.models.User;
import org.springframework.acl.mongodb.repositories.RoleRepository;
import org.springframework.acl.mongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections.IteratorUtils.toList;

@Component
public class AclManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        return toList(this.roleRepository.findAll().iterator());
    }

    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void delete(Role role) {
        this.roleRepository.delete(role);
    }

    public void delete(User user) {
        this.userRepository.delete(user);
    }

    public void addPermission(String roleId, EntityClass entityClass) {
        Role role = this.roleRepository.findOne(roleId);
        role.getEntityClasses().put(entityClass.getClassName(), entityClass);
        this.roleRepository.save(role);
    }

    public void deletePermission(String roleId, String className) {
        Role role = this.roleRepository.findOne(roleId);
        role.getEntityClasses().remove(className);
        this.roleRepository.save(role);
    }
 }