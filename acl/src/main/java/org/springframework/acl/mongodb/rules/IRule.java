package org.springframework.acl.mongodb.rules;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface IRule {
    String QUERY_TEMPLATE = "{ $and : [{ keys: { $in: ['%s'] } }, {'entityClasses.%s' : true, 'entityClasses.className' : '%s'}]}";
    String CREATE = "canCreate";

    String getKye();

    boolean process(MongoTemplate template, String aclKey, Object object);
}
