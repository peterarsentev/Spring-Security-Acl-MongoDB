package org.springframework.acl.mongodb.rules;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface IRule {
    String QUERY_TEMPLATE = "{ $and : [{ keys: { $in: ['%s'] } }, {'entityClasses.%s' : true, 'entityClasses.className' : '%s'}]}";

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

    boolean process(MongoTemplate template, String aclKey, Object object);
}
