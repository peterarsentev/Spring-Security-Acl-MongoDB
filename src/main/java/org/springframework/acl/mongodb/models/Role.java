package org.springframework.acl.mongodb.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "acl.role")
public class Role extends Base {
    @DBRef
    private List<EntityClass> entityClasses;

    public List<EntityClass> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<EntityClass> entityClasses) {
        this.entityClasses = entityClasses;
    }
}
