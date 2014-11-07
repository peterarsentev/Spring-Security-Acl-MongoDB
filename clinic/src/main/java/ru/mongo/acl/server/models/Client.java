package ru.mongo.acl.server.models;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Client extends Base {

    private String login;

    @DBRef
    private List<Pet> pets = new ArrayList<>();

    public Client() {}

    public Client(String id) {
        this.setId(id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                '}';
    }
}
