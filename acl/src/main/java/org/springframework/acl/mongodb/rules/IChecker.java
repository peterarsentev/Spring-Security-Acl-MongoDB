package org.springframework.acl.mongodb.rules;


import org.springframework.acl.mongodb.models.Base;

/**
 * TODO: comment
 * @author parsentev
 * @since 01.03.2016
 */
public interface IChecker<T> {
	/**
	 *
	 * @param template
	 * @param aclKey
	 * @param base
	 * @param can permission
	 * @return
	 */
	boolean process(T template, String aclKey, Base base, String can);
}
