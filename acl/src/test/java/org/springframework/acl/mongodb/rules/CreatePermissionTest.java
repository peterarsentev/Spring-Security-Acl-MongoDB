package org.springframework.acl.mongodb.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.acl.mongodb.models.CRUDBase;
import org.springframework.acl.mongodb.models.EntityClass;
import org.springframework.acl.mongodb.models.EntityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
@WebAppConfiguration
public class CreatePermissionTest {
    @Autowired
    private MongoTemplate template;

    @Test
    public void testProcess() throws Exception {
        ProtectedModel protectedModel = new ProtectedModel();
        protectedModel.setId("1");
        template.save(protectedModel);

        ReadableModel readableModel = new ReadableModel();
        readableModel.setId("1");
        template.save(readableModel);

        Acl acl = new Acl();
        acl.getKeys().add("admin");

        acl.getEntityClasses().add(build(new EntityClass(), ProtectedModel.class, true, true, true, true));
        acl.getEntityClasses().add(build(new EntityClass(), ReadableModel.class, false, true, false, false));

        EntityInstance protectedInstance = build(new EntityInstance(), ProtectedModel.class, true, true, true, true);
        protectedInstance.setInstanceId(protectedModel.getId());
        acl.getEntityInstances().add(protectedInstance);

        EntityInstance readInstance = build(new EntityInstance(), ReadableModel.class, false, true, false, false);
        readInstance.setInstanceId(readableModel.getId());
        acl.getEntityInstances().add(readInstance);

        template.save(acl);

	    final MongoRuleChecker checker = new MongoRuleChecker();

        assertTrue(new CreatePermission(checker).process(template, "admin", new ProtectedModel(), "canCreate"));
        assertTrue(new ReadPermission(checker).process(template, "admin", new ProtectedModel("1"), "canRead"));
        assertTrue(new ReadPermission(checker).process(template, "admin", new ReadableModel("1"), "canRead"));
        assertFalse(new UpdatePermission(checker).process(template, "admin", new ReadableModel("1"), "canUpdate"));
    }

    private <T extends CRUDBase> T build(T entity, Class cl, boolean create, boolean read, boolean update, boolean delete) {
        entity.setClassName(cl.getName());
        entity.setCanCreate(create);
        entity.setCanRead(read);
        entity.setCanUpdate(update);
        entity.setCanDelete(delete);
        return entity;
    }

}