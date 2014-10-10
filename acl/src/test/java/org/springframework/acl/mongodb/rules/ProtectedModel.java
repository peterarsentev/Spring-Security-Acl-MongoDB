package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "protectedModel")
public class ProtectedModel extends Base {

    public ProtectedModel() {
    }


    public ProtectedModel(String id) {
        this.setId(id);
    }
}
