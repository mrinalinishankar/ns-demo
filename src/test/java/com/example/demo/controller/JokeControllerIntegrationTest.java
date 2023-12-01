package com.example.demo.controller;

import com.example.demo.service.JokeApiService;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

/**
 * This integration test uses @SpringBootTest that loads the complete spring
 * application context.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class JokeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JokeApiService jokeApiService;

    @Test
    public void testGetJoke() throws Exception {
        // mock the external service
        when(jokeApiService.fetchDataFromJokeApi()).thenReturn(TestUtils.getJoke());

        // Make the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/joke"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"randomJoke\":\"Never date a baker. They're too kneady.\"}"));
    }
}
