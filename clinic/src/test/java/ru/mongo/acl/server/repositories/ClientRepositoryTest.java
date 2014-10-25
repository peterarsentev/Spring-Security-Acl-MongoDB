package ru.mongo.acl.server.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.mongo.acl.server.models.Client;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
@WebAppConfiguration
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    @Test
    public void create() {
        for (int i=0;i!=10;++i) {
            Client client = new Client();
            client.setLogin("login " + i);
            this.clientRepository.save(client);
        }
    }
}