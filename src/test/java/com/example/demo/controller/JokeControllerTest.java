package com.example.demo.controller;

import com.example.demo.service.JokeApiService;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

/**
 * This unit test focuses on testing the JokeController,
 * without starting the whole Spring context.
 * MockMvc is used to perform and verify HTTP requests.
 */
@WebMvcTest(JokeController.class)
public class JokeControllerTest {
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
