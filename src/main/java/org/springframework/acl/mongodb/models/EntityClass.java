package org.springframework.acl.mongodb.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "acl.entity.class")
public class EntityClass extends CRUDBase {
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}