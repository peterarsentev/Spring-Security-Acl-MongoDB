package ru.mongo.acl.models;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pet")
public class Pet extends Base {
    private String name;
    private Client owner;

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
