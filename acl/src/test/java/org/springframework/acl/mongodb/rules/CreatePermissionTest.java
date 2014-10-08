package org.springframework.acl.mongodb.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.acl.mongodb.models.EntityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
@WebAppConfiguration
public class CreatePermissionTest {
    @Autowired
    private MongoTemplate template;

    @Test
    public void testProcess() throws Exception {
//        Acl acl = new Acl();
//        acl.getKeys().add("admin");
//        acl.getEntityClasses().add(build(ProtectedModel.class, true, true, true, true));
////        acl.getEntityClasses().add(build(ReadableModel.class, false, true, false, false));
//
//        template.save(acl);
//
//        acl = new Acl();
//        acl.getKeys().add("client");
//        acl.getEntityClasses().add(build(ProtectedModel.class, true, false, false, false));
////        acl.getEntityClasses().add(build(ReadableModel.class, false, true, false, false));
//
//        template.save(acl);


        CreatePermission createPermission = new CreatePermission();
        assertTrue(createPermission.process(template, "admin", new ProtectedModel()));
    }

    private EntityClass build(Class cl, boolean create, boolean read, boolean update, boolean delete) {
        EntityClass entityClass = new EntityClass();
        entityClass.setClassName(cl.getName());
        entityClass.setCanCreate(create);
        entityClass.setCanRead(read);
        entityClass.setCanUpdate(update);
        entityClass.setCanDelete(delete);
        return entityClass;
    }

}