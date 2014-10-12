Spring Security ACL MongoDB
==================

This project implements the ACL mechanism. It uses the MongoDB as persistent store.
The project has the two modules :
 - acl (implementation of ACL mechanism)
 - clinic (example how to use this implementation)
 
How to use this library
-----------------------

First of all the project should configured the Spring Security. 
Below show the config from the example (clinic)

<pre>
<code>
    <http auto-config="true" use-expressions="true">
        <intercept-url pattern="/api**" access="isAuthenticated()" />
    </http>
</code>
</pre>

clinic/src/resources/security-context.xml

 
 
Core implementation
-------------------

The main idea of this mechanism is implementation the org.springframework.security.access.PermissionEvaluator interface.



if you have any questions, feel free to contact me.
