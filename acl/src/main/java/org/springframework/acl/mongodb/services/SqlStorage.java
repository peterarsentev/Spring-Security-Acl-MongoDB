package org.springframework.acl.mongodb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * TODO: comment
 * @author parsentev
 * @since 02.03.2016
 */
public class SqlStorage implements AclManager {
	private static final Logger log = LoggerFactory.getLogger(SqlStorage.class);
	private final JdbcTemplate template;

	public SqlStorage(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void save(Acl acl) {

	}

	@Override
	public void save(List<Acl> it) {

	}

	@Override
	public List<Acl> findAll() {
		return null;
	}

	@Override
	public void delete(String id) {

	}
}
