package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.MongoTemplate;

public interface IRule {
    String QUERY_TEMPLATE_INSTANCE = "{keys : {$in : ['%s']}, " +
            "entityClasses : {$elemMatch : {className : '%s', '%s' : true}}," +
            "entityInstances : {$elemMatch : {className : '%s', %s : true, instanceId : '%s'}}}";

    String QUERY_TEMPLATE_CLASS = "{keys : {$in : ['%s']}, " +
            "entityClasses : {$elemMatch :{className : '%s', %s : true}}}";

    public enum Can {
        CREATE("canCreate"),
        UPDATE("canUpdate"),
        READ("canRead"),
        DELETE("canDelete");

        private final String key;

        Can(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    String getKey();

    boolean process(MongoTemplate template, String aclKey, Base base);
}
