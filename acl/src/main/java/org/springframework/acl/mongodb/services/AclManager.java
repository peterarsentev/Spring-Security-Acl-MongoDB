package org.springframework.acl.mongodb.services;

import org.springframework.acl.mongodb.models.Acl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class AclManager {
    private final MongoTemplate template;

    public AclManager(MongoTemplate template) {
        this.template = template;
    }

	public void save(Acl acl) {
		this.template.save(acl);
	}

	public void save(List<Acl> it) {
		this.template.insert(it, Acl.class);
	}

	public List<Acl> findAll() {
		return this.template.findAll(Acl.class);
	}

	public void delete(String id) {
		this.template.findAndRemove(new Query().addCriteria(new Criteria("id").is(id)), Acl.class);
	}
}