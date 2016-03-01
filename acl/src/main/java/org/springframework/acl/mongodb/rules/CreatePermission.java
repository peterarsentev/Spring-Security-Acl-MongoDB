package org.springframework.acl.mongodb.rules;

import org.springframework.acl.mongodb.models.Base;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CreatePermission<T> extends BasePermission<T> implements IRule<T> {

	public CreatePermission(IChecker<T> checker) {
		super(checker);
	}

	@Override
    public String getKey() {
        return "create";
    }

    @Override
    public Can getCanField() {
        return Can.CREATE;
    }
}
