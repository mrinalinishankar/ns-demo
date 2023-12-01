package com.example.demo.service;

import com.example.demo.dto.Joke;
import com.example.demo.dto.Jokes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeApiService {
    private final String apiUrl;

    @Autowired
    private final RestTemplate restTemplate;

    public JokeApiService(RestTemplate restTemplate,
            @Value("${external.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    /**
     * Filter the list of jokes returned by the external API on the following conditions:
     * It should not be sexist
     * It should not be explicit
     * It should be safe to display
     * It should be the shortest joke in the list
     * @return joke
     */
    public Joke fetchDataFromJokeApi() {
        Jokes response = restTemplate.getForObject(apiUrl, Jokes.class);
        List<Joke> jokesList = response.jokes();

        List<Joke> filteredList = filterJokes(jokesList);

        return findShortestJoke(filteredList);
    }

    private List<Joke> filterJokes(List<Joke> jokesList) {
        return jokesList.stream()
                .filter(filteredJokes -> Boolean.valueOf(true).equals(filteredJokes.safe()))
                .filter(filteredJokes -> Boolean.valueOf(false).equals(filteredJokes.flags().explicit()))
                .filter(filteredJokes -> Boolean.valueOf(false).equals(filteredJokes.flags().sexist()))
                .collect(Collectors.toList());
    }

    private Joke findShortestJoke(List<Joke> jokeList) {
        return jokeList.stream()
                .min((joke1, joke2) -> Integer.compare(joke1.joke().length(), joke2.joke().length()))
                .orElse(null);
    }
}

