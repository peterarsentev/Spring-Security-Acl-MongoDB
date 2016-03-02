package org.springframework.acl.mongodb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * TODO: comment
 * @author parsentev
 * @since 02.03.2016
 */
public class MongoStorage implements AclManager {
	private static final Logger log = LoggerFactory.getLogger(MongoStorage.class);

	private final MongoTemplate template;

	public MongoStorage(MongoTemplate template) {
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
