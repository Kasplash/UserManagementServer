package com.example.UserManagement;

import com.example.UserManagement.controller.UserController;
import com.example.UserManagement.models.User;
import com.example.UserManagement.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;


@WebMvcTest(UserController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private UserRepository service;

    @Test
    public void UserShouldBeReturnedWhenPost() throws Exception {
        Long personalNumber = new Random().nextLong();
        String name = "Random Name";
        Date dateofbirth = new Date();

        User user = new User(personalNumber, name, dateofbirth);

        System.out.print(objectMapper.writeValueAsString(user));

        /**
        when(service.save(any())).thenReturn("This is a response");
        this.mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                    .andDo(print()).andExpect(status().isOk())
                        .andExpect((ResultMatcher) content().string(containsString("This is a response")));

         */
    }

    @Test
    public void UsersShouldBeReturnedWhenGet() throws Exception {
        Long personalNumber = new Random().nextLong();
        String name = "Random Name";
        Date dateofbirth = new Date();

        User user = new User(personalNumber, name, dateofbirth);
        List<User> userList = new ArrayList<>();
        userList.add(user);

         when(service.findAll()).thenReturn(userList);
         this.mockMvc.perform(get("/users"))
         .andDo(print()).andExpect(status().isOk())
         .andExpect(jsonPath("$[0].name").value("Random Name"));
    }
}
