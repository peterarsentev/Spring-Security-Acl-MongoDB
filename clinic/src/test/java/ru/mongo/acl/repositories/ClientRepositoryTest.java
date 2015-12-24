package ru.mongo.acl.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.acl.mongodb.models.Acl;
import org.springframework.acl.mongodb.services.AclManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.mongo.acl.models.Client;
import ru.mongo.acl.models.Pet;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
@WebAppConfiguration
public class ClientRepositoryTest {

	@Autowired
	private MongoTemplate template;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private AclManager aclManager;

	@Test
	public void initDb() throws IOException {
		template.getDb().dropDatabase();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		clientRepository.save(
				Arrays.asList(
						mapper.readValue(
								this.getClass().getResourceAsStream("/clients.json"),
								Client[].class
						)
				)
		);
		petRepository.save(
				Arrays.asList(
						mapper.readValue(
								this.getClass().getResourceAsStream("/pets.json"),
								Pet[].class
						)
				)
		);
		aclManager.save(
				Arrays.asList(
						mapper.readValue(
								this.getClass().getResourceAsStream("/acls.json"),
								Acl[].class
						)
				)
		);
	}
}