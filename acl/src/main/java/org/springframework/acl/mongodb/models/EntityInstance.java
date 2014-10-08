package org.springframework.acl.mongodb.models;

public class EntityInstance extends CRUDBase {
    private EntityClass entityClass;
    private String instanceId;

    public EntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
