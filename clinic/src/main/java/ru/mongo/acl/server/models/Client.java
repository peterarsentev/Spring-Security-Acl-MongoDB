package ru.mongo.acl.server.models;

import org.springframework.acl.mongodb.models.Base;

public class Client extends Base {

    private String login;

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

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                '}';
    }
}
