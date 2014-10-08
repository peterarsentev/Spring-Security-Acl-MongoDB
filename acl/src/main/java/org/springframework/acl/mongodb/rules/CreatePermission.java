package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Acl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

public class CreatePermission implements IRule {

    @Override
    public String getKye() {
        return "create";
    }

    @Override
    public boolean process(MongoTemplate template, String aclKey, Object object) {
        return template.count(new BasicQuery(String.format(QUERY_TEMPLATE, aclKey, CREATE, object.getClass().getName())), Acl.class) > 0;
    }
}
