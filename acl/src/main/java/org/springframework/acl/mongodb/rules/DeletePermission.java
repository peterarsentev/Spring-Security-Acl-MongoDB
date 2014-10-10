package org.springframework.acl.mongodb.rules;

public class DeletePermission extends CRUDRule implements IRule {

    @Override
    public String getKey() {
        return "delete";
    }

    @Override
    Can getCanField() {
        return Can.DELETE;
    }
}
