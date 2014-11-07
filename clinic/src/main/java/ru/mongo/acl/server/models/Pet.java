package ru.mongo.acl.server.models;

import org.springframework.acl.mongodb.models.Base;

public class Pet extends Base {

    private String clientId;

    private String name;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
