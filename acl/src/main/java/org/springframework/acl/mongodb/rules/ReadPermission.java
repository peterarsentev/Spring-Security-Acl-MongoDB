package org.springframework.acl.mongodb.rules;

public class ReadPermission extends CRUDRule implements IRule {

    @Override
    public String getKey() {
        return "read";
    }

    @Override
    Can getCanField() {
        return Can.READ;
    }
}
