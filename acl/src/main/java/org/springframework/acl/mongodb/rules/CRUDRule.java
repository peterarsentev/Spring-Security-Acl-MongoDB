package org.springframework.acl.mongodb.rules;

import org.slf4j.Logger;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

import static java.lang.String.format;

public abstract class CRUDRule implements IRule {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CRUDRule.class);

    abstract Can getCanField();

    @Override
    public boolean process(MongoTemplate template, String aclKey, Base base) {
        String query;
        if (base.getId() != null) {
            query = format(QUERY_TEMPLATE_INSTANCE, aclKey, base.getClass().getName(), this.getCanField().getKey(),
                    base.getClass().getName(), this.getCanField().getKey(), base.getId());
        } else {
            query = format(QUERY_TEMPLATE_CLASS, aclKey, base.getClass().getName(), this.getCanField().getKey());
        }
        LOGGER.info("query : {}", query);
        return template.exists(new BasicQuery(query), Acl.class);
    }
}
