package org.springframework.acl.mongodb.rules;

public class UpdatePermission extends CRUDRule implements IRule {

    @Override
    public String getKey() {
        return "update";
    }

    @Override
    Can getCanField() {
        return Can.UPDATE;
    }
}
