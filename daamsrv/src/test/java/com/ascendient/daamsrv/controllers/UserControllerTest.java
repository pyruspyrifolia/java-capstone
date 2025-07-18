package com.ascendient.daamsrv.controller;

import com.ascendient.daamsrv.entity.User;
import com.ascendient.daamsrv.service.UserService; // Assume you have a UserService
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class) // Replace UserController with your actual User controller class
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Mock your UserService

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetUserByIdSuccessfully() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setFirst("Test");
        user.setLast("User");
        user.setEmail("test@example.com");
        user.setRoles("USER");

        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.first").value("Test"))
                .andExpect(jsonPath("$.last").value("User"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.roles").value("USER"));
    }
}