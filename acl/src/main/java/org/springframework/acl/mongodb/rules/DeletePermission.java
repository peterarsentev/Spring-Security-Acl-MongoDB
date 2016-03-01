package org.springframework.acl.mongodb.rules;

import org.springframework.data.mongodb.core.MongoTemplate;

public class DeletePermission<T> extends BasePermission<T> implements IRule<T> {

	public DeletePermission(IChecker<T> checker) {
		super(checker);
	}

	@Override
    public String getKey() {
        return "delete";
    }

    @Override
    public Can getCanField() {
        return Can.DELETE;
    }
}
