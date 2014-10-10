package org.springframework.acl.mongodb.rules;

public class CreatePermission extends CRUDRule implements IRule {

    @Override
    public String getKey() {
        return "create";
    }

    @Override
    Can getCanField() {
        return Can.CREATE;
    }
}
