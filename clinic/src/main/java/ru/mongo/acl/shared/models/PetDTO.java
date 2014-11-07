package ru.mongo.acl.shared.models;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.mongo.acl.server.models.Client;

public class PetDTO implements IPetDTO {
    private String clientId;
    private String name;

    public PetDTO() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
