package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.MongoTemplate;

public interface IRule<T> extends IChecker<T> {
	enum Can {
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

	Can getCanField();
}
