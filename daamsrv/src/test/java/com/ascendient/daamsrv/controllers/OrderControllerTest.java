package com.ascendient.daamsrv.controller;

import com.ascendient.daamsrv.entity.Order;
import com.ascendient.daamsrv.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService; 

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetOrderByIdSuccessfully() throws Exception {
        Order order = new Order();
        order.setId(1001L);
        order.setUserid(1L);
        order.setOrdertime(LocalDateTime.now());
        order.setStatus("placed");

        when(orderService.findById(1001L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/1001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1001))
                .andExpect(jsonPath("$.userid").value(1))
                .andExpect(jsonPath("$.status").value("placed"));
    }

    // Extra Mile: Add unit tests for other Order endpoints (`GET ALL`, `POST`, `PUT`, `DELETE`)
    // and edge cases.
}