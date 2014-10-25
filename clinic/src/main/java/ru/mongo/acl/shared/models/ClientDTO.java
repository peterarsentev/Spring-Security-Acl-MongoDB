package ru.mongo.acl.shared.models;

public class ClientDTO implements IClientDTO {
    private String id;
    private String login;

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

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"login\":\"" + login + "\"}";
    }
}
