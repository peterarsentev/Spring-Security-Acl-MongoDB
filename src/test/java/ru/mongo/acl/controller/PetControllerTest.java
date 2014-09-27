package ru.mongo.acl.controller;

import org.junit.Test;
import ru.mongo.acl.tools.SpringTest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class PetControllerTest extends SpringTest {
    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/create/")
//                .content(null)
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print());
    }
}