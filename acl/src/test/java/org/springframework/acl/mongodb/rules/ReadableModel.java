package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "readableModel")
public class ReadableModel extends Base {
    public ReadableModel() {
    }


    public ReadableModel(String id) {
        this.setId(id);
    }
}
