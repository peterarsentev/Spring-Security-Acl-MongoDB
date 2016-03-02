package org.springframework.acl.mongodb.services;

import org.springframework.acl.mongodb.models.Acl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface AclManager {

	 void save(Acl acl);

	 void save(List<Acl> it);

	 List<Acl> findAll();

	 void delete(String id);
}