Spring Security ACL MongoDB
==================

http://www.youtube.com/watch?v=x0UE5THrSZM 

This project implements the ACL mechanism. It uses the MongoDB as persistent storage.
The project has the two modules :

- acl (implementation of ACL mechanism)
- clinic (example how to use this implementation)
 
Core concept
------------

Because this implementation uses the MongoDB as persistence storage, the main document can have flexible structure, 
which provide great performance for checking permissions. 
The main document has the follow structure:

    { 
        name : "...",
        keys : ["", "", ...]
        entityClasses : [
                            {
                                className : "...",
                                canCreate : true/false,
                                canRead : true/false,
                                canUpdate : true/false,
                                canDelete : true/false
                            } ,
                            ...
                        ],
        entityInstances : [
                                {
                                      className : "...",
                                      instanceId : "...",
                                      canCreate : true/false
                                      canRead : true/false,
                                      canUpdate : true/false,
                                      canDelete : true/false
                                },
                                ...
                          ]
    }
, where 

- name - Acl name, for example: Manager Permission, Client Permission
- keys - Array of authorization username, for example (admin, user, manager)
- entityClasses - Array of permissions for classes, It has the follow fields:
    - className - full class name, for example **ru.mongo.acl.shared.models.Pet**
    - canCreate - create permission for class
    - canRead - read permission for class
    - canUpdate - update permission for class
    - canDelete - delete permission for class
- entityInstances - Array of permissions for instances, It has the follow fields
    - className - full class name, for example **ru.mongo.acl.shared.models.Pet**
    - instanceId - the instance id 
    - canCreate - create permission for instance
    - canRead - read permission for instance
    - canUpdate - update permission for instance
    - canDelete - delete permission for instance
    
This structure is described by **org.springframework.acl.mongodb.models.Acl** class.
 
Configuration
-------------

First of all the project should configured the Spring Security. 
Below it is shown the config of Spring Security from the example (clinic)

*clinic/src/resources/security-context.xml*

    <http auto-config="true" use-expressions="true">
        <intercept-url pattern="/api**" access="isAuthenticated()" />
    </http>

    <authentication-manager>
        <authentication-provider>
            <user-service>
                <user name="admin" password="password" authorities="ROLE_ADMIN" />
                <user name="user" password="password" authorities="ROLE_USER" />
                <user name="visitor" password="password" authorities="ROLE_VISITOR" />
            </user-service>
        </authentication-provider>
    </authentication-manager>

Then you should enable the Spring expression handler, which is used the implementation of this library

    <global-method-security pre-post-annotations="enabled" proxy-target-class="true">
        <expression-handler ref="expressionHandler" />
    </global-method-security>

    <beans:bean id="permissionEvaluator" class="org.springframework.acl.mongodb.rules.MongoDBPermissionEvaluator">
        <beans:constructor-arg ref="mongoTemplate"/>
        <beans:constructor-arg name="rules">
            <beans:list>
                <beans:bean class="org.springframework.acl.mongodb.rules.CreatePermission"/>
                <beans:bean class="org.springframework.acl.mongodb.rules.ReadPermission"/>
                <beans:bean class="org.springframework.acl.mongodb.rules.UpdatePermission"/>
                <beans:bean class="org.springframework.acl.mongodb.rules.DeletePermission"/>
            </beans:list>
        </beans:constructor-arg>
    </beans:bean>

    <beans:bean id="expressionHandler"
                class="org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler"
                p:permissionEvaluator-ref="permissionEvaluator"/>
                
The class **org.springframework.acl.mongodb.rules.MongoDBPermissionEvaluator** is the implementation 
of the **org.springframework.security.access.PermissionEvaluator** interface. 

This class has the constructor : **public MongoDBPermissionEvaluator(MongoTemplate template, Collection<IRule> rules)**.
This constructor should take the two parameters : 

 - MongoTemplate - Spring Data MongoTemplate. The clinic example uses the reference bean for MongoTemplate.
 
*clinic/src/resources/servlet-context.xml*

    <mongo:mongo host="127.0.0.1" port="27017" />
    <mongo:db-factory id="mongoDbFactory" dbname="mongo_acl" />

    <beans:bean id="mongoTemplate" class="org.springframework.data.mongodb.core.MongoTemplate">
        <beans:constructor-arg name="mongoDbFactory" ref="mongoDbFactory" />
    </beans:bean>
   
This bean has the configuration settings for connection to MongoDB
    
 - Collection<IRule> rules - The collection of the rules.
This library has the four implementations of **org.springframework.acl.mongodb.rules.IRule**
    - org.springframework.acl.mongodb.rules.CreatePermission - check the create access
    - org.springframework.acl.mongodb.rules.ReadPermission - check the read access
    - org.springframework.acl.mongodb.rules.UpdatePermission - check the update access
    - org.springframework.acl.mongodb.rules.DeletePermission - check the delete access

if you have the specific business cases, you can add the own rules in this list. The clinic example has the external 
rule **ru.mongo.acl.server.rules.ManagerPermission**

After that you should only add the **org.springframework.acl.mongodb.services.AclManager**. 
This class is used for manage the ACL. It has the methods for create the ACL instance, add the permissions for classes,
add the permission for instances.

*clinic/src/resources/servlet-context.xml*

    <beans:bean class="org.springframework.acl.mongodb.services.AclManager">
        <beans:constructor-arg ref="mongoTemplate"/>
    </beans:bean>

Usage
-----
The usage keeps as the general concept of Spring Security Acl. You should mark the necessary methods by specific annotations.
For example : the **ru.mongo.acl.server.controller.IGRUDController** interface

    public interface IGRUDController<T> {
        @PreAuthorize("hasPermission(#pet, 'create')")
        T create(T t);
    
        @PreAuthorize("hasPermission(#pet, 'update')")
        T update(T t);
    
        @PreAuthorize("hasPermission(#pet, 'delete')")
        void delete(T t);
    
        @PostFilter("hasPermission(filterObject, 'read')")
        List<T> getByOwner(@PathVariable String clientId);
    
        @PostAuthorize("hasPermission(returnObject, 'read')")
        T geById(@PathVariable String id);
    }

Core implementation
-------------------

The main idea of this mechanism is implementation the org.springframework.security.access.PermissionEvaluator interface.

TODO list
---------
- Finish AclManager (CRUD - acl instances)
- Finish the clinic example
- Cover the code to unit tests
- Add the performance tests
- Add the exhaustive JavaDoc

Contact
-------

if you have any questions, feel free to contact me. Skype : petrarsentev
