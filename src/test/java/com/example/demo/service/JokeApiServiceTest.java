package com.example.demo.service;

import com.example.demo.dto.Joke;
import com.example.demo.dto.Jokes;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class JokeApiServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private JokeApiService jokeApiService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchDataFromJokeApiWhenJokesReturnedAreSafe() {
        //given
        String apiUrl = "https://mock.v2.jokeapi.dev/joke";

        Mockito.when(restTemplate.getForObject(apiUrl, Jokes.class))
                .thenReturn(TestUtils.getJokes(true, false, false));

        //when
        JokeApiService jokeApiService = new JokeApiService(restTemplate, apiUrl);
        Joke joke = jokeApiService.fetchDataFromJokeApi();

        //then
        assertEquals(joke.joke(), "Never date a baker. They're too kneady.");
        assertFalse(joke.flags().explicit());
        assertFalse(joke.flags().sexist());
        assertTrue(joke.safe());
    }

    @Test
    public void testFetchDataFromJokeApiWhenJokesReturnedAreNotSafe() {
        //given
        String apiUrl = "https://mock.v2.jokeapi.dev/joke";

        Mockito.when(restTemplate.getForObject(apiUrl, Jokes.class))
                .thenReturn(TestUtils.getJokes(false, false, false));

        //when
        JokeApiService jokeApiService = new JokeApiService(restTemplate, apiUrl);
        Joke joke = jokeApiService.fetchDataFromJokeApi();

        //then
        assertEquals(joke.joke(), "To whoever stole my copy of Microsoft Office, I will find you. You have my Word!");
        assertFalse(joke.flags().explicit());
        assertFalse(joke.flags().sexist());
        assertTrue(joke.safe());
    }
}
