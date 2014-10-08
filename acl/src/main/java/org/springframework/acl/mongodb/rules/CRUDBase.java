package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Acl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

public abstract class CRUDBase implements IRule {

    abstract Can getCanField();

    @Override
    public boolean process(MongoTemplate template, String aclKey, Object object) {
        return template.exists(new BasicQuery(String.format(QUERY_TEMPLATE, aclKey, this.getCanField().getKey(), object.getClass().getName())), Acl.class);
    }
}
