package org.springframework.acl.mongodb.rules;

import org.slf4j.Logger;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

import static java.lang.String.format;

public class MongoRuleChecker implements IChecker<MongoTemplate> {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MongoRuleChecker.class);

	private static final String QUERY_TEMPLATE_INSTANCE = "{keys : {$in : ['%s']}, " +
	                                 "entityClasses : {$elemMatch : {className : '%s', '%s' : true}}," +
	                                 "entityInstances : {$elemMatch : {className : '%s', %s : true, instanceId : '%s'}}}";

	private static final String QUERY_TEMPLATE_CLASS = "{keys : {$in : ['%s']}, " +
	                              "entityClasses : {$elemMatch :{className : '%s', %s : true}}}";

	@Override
    public boolean process(MongoTemplate template, String aclKey, Base base, final String can) {
        final String query;
        if (base.getId() != null) {
            query = format(QUERY_TEMPLATE_INSTANCE, aclKey, base.getClass().getName(), can,
                    base.getClass().getName(), can, base.getId());
        } else {
            query = format(QUERY_TEMPLATE_CLASS, aclKey, base.getClass().getName(), can);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("query : {}", query);
        }
        return template.exists(new BasicQuery(query), Acl.class);
    }
}
