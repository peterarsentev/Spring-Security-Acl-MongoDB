package org.springframework.acl.mongodb.services;

import org.springframework.data.mongodb.core.MongoTemplate;

public class AclManager {
    private final MongoTemplate template;

    public AclManager(MongoTemplate template) {
        this.template = template;
    }
}