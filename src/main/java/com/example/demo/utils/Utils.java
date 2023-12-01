package com.example.demo.utils;

import com.example.demo.dto.Joke;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Utils {

    public static HashMap<String, Object> getCustomResponse(Joke joke) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", joke.id());
        map.put("randomJoke", joke.joke());
        return map;
    }
}
