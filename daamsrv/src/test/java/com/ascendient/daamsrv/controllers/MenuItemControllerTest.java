package com.ascendient.daamsrv.controller;

import com.ascendient.daamsrv.entity.MenuItem;
import com.ascendient.daamsrv.service.MenuItemService;
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

@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemService menuItemService; 

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetMenuItemByIdSuccessfully() throws Exception {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Bison Burger");
        menuItem.setDescription("Detailed description of the burger");
        menuItem.setCategory("main");
        menuItem.setPrice(11.54);
        menuItem.setImageUrl("/images/food/bison_burger.jpg");
        menuItem.setAvailable(true);

        when(menuItemService.findById(1L)).thenReturn(Optional.of(menuItem));

        mockMvc.perform(get("/api/menu_items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Bison Burger"))
                .andExpect(jsonPath("$.price").value(11.54))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void testGetMenuItemByIdNotFound() throws Exception {
        when(menuItemService.findById(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/menu_items/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Expecting 404 Not Found
    }
}