package org.springframework.acl.mongodb.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.jdbc.core.JdbcTemplate;

import static java.lang.String.format;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.03.2016
 */
public class SQLRuleChecker implements IChecker<JdbcTemplate> {
	private static final Logger log = LoggerFactory.getLogger(SQLRuleChecker.class);

	private static final String QUERY_TEMPLATE_INSTANCE = "select count(acl.id) from ss_acl as acl " +
	                                                      "inner join ss_key as key on acl.id = key.acl_id and key.key = ? " +
	                                                      "inner join ss_instance as instance on acl.id = instance.acl_id and instance.class_name = ? and " +
	                                                      "instance.instance_id=? and instance.%s is true;";

	private static final String QUERY_TEMPLATE_CLASS = "select count(acl.id) from ss_acl as acl " +
	                                                   "inner join ss_key as key on acl.id = key.acl_id and key.key = ? " +
	                                                   "inner join ss_class as class on acl.id = class.acl_id and class.class_name = ? and class.%s is true;";

	@Override
	public boolean process(JdbcTemplate template, String aclKey, Base base, final String can) {
		final Integer count;
		if (base.getId() != null) {
			count = template.queryForObject(
					String.format(QUERY_TEMPLATE_INSTANCE, can), Integer.class,
					aclKey, base.getClass().getName(), base.getId());
		} else {
			count = template.queryForObject(
					String.format(QUERY_TEMPLATE_CLASS, can),
					Integer.class, aclKey, base.getClass().getName());
		}
		return count > 0;
	}
}
