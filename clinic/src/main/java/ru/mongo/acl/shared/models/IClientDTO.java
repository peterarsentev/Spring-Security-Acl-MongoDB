package ru.mongo.acl.shared.models;

import java.io.Serializable;
import java.util.List;

public interface IClientDTO extends Serializable {
    String getId();

    void setId(String id);

    String getLogin();

    void setLogin(String login);

    List<IPetDTO> getPets();

    void setPets(List<IPetDTO> pets);
}
