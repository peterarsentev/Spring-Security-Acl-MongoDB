package org.springframework.acl.mongodb.rules;

import org.springframework.data.mongodb.core.MongoTemplate;

public class ReadPermission<T> extends BasePermission<T> implements IRule<T> {

	public ReadPermission(IChecker<T> checker) {
		super(checker);
	}

	@Override
    public String getKey() {
        return "read";
    }

    @Override
    public Can getCanField() {
        return Can.READ;
    }
}
