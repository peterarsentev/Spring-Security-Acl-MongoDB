package org.springframework.acl.mongodb.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.acl.mongodb.models.Base;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.03.2016
 */
public class BasePermission<T> implements IChecker<T> {
	private static final Logger log = LoggerFactory.getLogger(BasePermission.class);

	private final IChecker<T> checker;

	public BasePermission(IChecker<T> checker) {
		this.checker = checker;
	}

	@Override
	public boolean process(T template, String aclKey, Base base, String can) {
		return this.checker.process(template, aclKey, base, can);
	}
}
