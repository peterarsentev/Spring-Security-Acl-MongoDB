package ru.mongo.acl.shared.models;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.mongo.acl.server.models.Client;

@Document(collection = "pet")
public class Pet extends Base {
    private String name;
    private Client owner;

    public Pet() {

    }

    public Pet(String id) {
        this.setId(id);
    }


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
