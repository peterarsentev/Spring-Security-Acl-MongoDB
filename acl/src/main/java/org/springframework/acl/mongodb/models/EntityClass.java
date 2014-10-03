package org.springframework.acl.mongodb.models;

public class EntityClass extends CRUDBase {
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}