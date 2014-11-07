package ru.mongo.acl.shared.models;

import java.io.Serializable;

/**
 * Created by parsentev on 07.11.14.
 */
public interface IPetDTO extends Serializable {
    String getName();

    void setName(String name);

    String getClientId();

    void setClientId(String clientId);
}
