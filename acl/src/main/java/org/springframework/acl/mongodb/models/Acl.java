package org.springframework.acl.mongodb.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "acl")
public class Acl extends Base {
    private String name;

    private List<String> keys = new ArrayList<String>();

    private List<EntityClass> entityClasses = new ArrayList<EntityClass>();

    private List<EntityInstance> entityInstances = new ArrayList<EntityInstance>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityClass> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<EntityClass> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public List<EntityInstance> getEntityInstances() {
        return entityInstances;
    }

    public void setEntityInstances(List<EntityInstance> entityInstances) {
        this.entityInstances = entityInstances;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
