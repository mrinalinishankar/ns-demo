package com.example.demo.controller;

import com.example.demo.dto.Joke;
import com.example.demo.service.JokeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.example.demo.utils.Utils.getCustomResponse;

@RestController
@RequestMapping("/api")
public class JokeController {

    private final JokeApiService jokeApiService;

    @Autowired
    public JokeController(JokeApiService jokeApiService) {
        this.jokeApiService = jokeApiService;
    }

    @GetMapping("/joke")
    public HashMap<String, Object> getJoke() {
        Joke joke = jokeApiService.fetchDataFromJokeApi();
        return getCustomResponse(joke);
    }
}
