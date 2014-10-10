package org.springframework.acl.mongodb.models;

public class EntityInstance extends CRUDBase {

    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}