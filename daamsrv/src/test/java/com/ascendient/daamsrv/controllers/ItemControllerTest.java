package com.ascendient.daamsrv.controller;

import com.ascendient.daamsrv.entity.Item;
import com.ascendient.daamsrv.service.ItemService; // Assume you have an ItemService
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

@WebMvcTest(ItemController.class) // Replace ItemController with your actual Item controller class
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService; // Mock your ItemService

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetItemByIdSuccessfully() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setOrderid(1001L);
        item.setItemid(10L); // Corresponds to a MenuItem ID
        item.setPrice(11.54);
        item.setNotes("hold the mayo");
        item.setFirstname("John");

        when(itemService.findById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.orderid").value(1001))
                .andExpect(jsonPath("$.itemid").value(10))
                .andExpect(jsonPath("$.price").value(11.54))
                .andExpect(jsonPath("$.notes").value("hold the mayo"))
                .andExpect(jsonPath("$.firstname").value("John"));
    }
}