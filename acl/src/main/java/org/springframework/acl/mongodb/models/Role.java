package org.springframework.acl.mongodb.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.Map;

@Document(collection = "acl.role")
public class Role extends Base {
    private String name;
    private Map<String, EntityClass> entityClasses = new LinkedHashMap<String, EntityClass>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, EntityClass> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(Map<String, EntityClass> entityClasses) {
        this.entityClasses = entityClasses;
    }
}
