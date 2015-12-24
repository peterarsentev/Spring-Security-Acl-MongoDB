package ru.mongo.acl.controller;

import org.junit.Ignore;
import org.junit.Test;
import ru.mongo.acl.models.Pet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mongo.acl.controller.JsonUtil.convert2Byte;

public class PetControllerTest extends SpringTest {

    @Ignore
    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/pet/")
                .content(convert2Byte(new Pet()))
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void pet() throws Exception {
        mockMvc.perform(get("/pet/1")
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void pets() throws Exception {
        mockMvc.perform(get("/pet/")
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Override
    String getLogin() {
        return "admin";
    }

    @Override
    String getPassword() {
        return "password";
    }
}