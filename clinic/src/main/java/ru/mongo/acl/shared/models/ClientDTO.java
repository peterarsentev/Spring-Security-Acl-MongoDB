package ru.mongo.acl.shared.models;

import java.util.List;

public class ClientDTO implements IClientDTO {
    private String id;
    private String login;
    private List<IPetDTO> pets;

    public List<IPetDTO> getPets() {
        return pets;
    }

    public void setPets(List<IPetDTO> pets) {
        this.pets = pets;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    public String toString() {
        return "{\"id\":\"" + id + "\", \"login\":\"" + login + "\"}";
    }
}
