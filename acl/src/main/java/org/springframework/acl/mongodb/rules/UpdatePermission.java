package org.springframework.acl.mongodb.rules;

public class UpdatePermission extends CRUDBase implements IRule {

    @Override
    public String getKey() {
        return "update";
    }

    @Override
    Can getCanField() {
        return Can.UPDATE;
    }
}
