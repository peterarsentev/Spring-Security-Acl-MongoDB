package org.springframework.acl.mongodb.rules;

import org.springframework.data.mongodb.core.MongoTemplate;

public class UpdatePermission<T> extends BasePermission<T> implements IRule<T> {

	public UpdatePermission(IChecker<T> checker) {
		super(checker);
	}

	@Override
    public String getKey() {
        return "update";
    }

    @Override
    public Can getCanField() {
        return Can.UPDATE;
    }
}
