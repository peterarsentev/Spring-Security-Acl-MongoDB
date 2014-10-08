package org.springframework.acl.mongodb.rules;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.*;

public class MongoDBPermissionEvaluator implements PermissionEvaluator {

    private final MongoTemplate template;

    private Map<String, IRule> rules = new LinkedHashMap<String, IRule>();

    public MongoDBPermissionEvaluator(MongoTemplate template, Collection<IRule> rules) {
        this.template = template;
        for (IRule rule : rules) {
            this.rules.put(rule.getKey(), rule);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        boolean result = false;
        for (String key : (Collection<String>) permission) {
            result = this.rules.get(key).process(template, authentication.getName(), target);
            if (!result) {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}