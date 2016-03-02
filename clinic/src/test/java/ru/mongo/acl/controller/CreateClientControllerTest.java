package ru.mongo.acl.controller;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.mongo.acl.models.Client;
import ru.mongo.acl.models.Pet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mongo.acl.controller.JsonUtil.convert2Byte;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.12.2015
 */
@ContextConfiguration(locations = { "classpath:security-context.xml", "classpath:sql/servlet-context.xml"})
public class CreateClientControllerTest extends SpringTest {

	@Override
	String getLogin() {
		return "user";
	}

	@Override
	String getPassword() {
		return "password";
	}

	@Test
	public void createClient() throws Exception {
		mockMvc.perform(post("/client/")
				.content(convert2Byte(new Client()))
				.contentType(APPLICATION_JSON_UTF8))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	public void deleteClient() throws Exception {
		mockMvc.perform(delete("/client/")
				.content(convert2Byte(new Client()))
				.contentType(APPLICATION_JSON_UTF8))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}