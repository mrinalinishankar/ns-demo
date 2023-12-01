package com.example.demo.utils;

import com.example.demo.dto.Flag;
import com.example.demo.dto.Joke;
import com.example.demo.dto.Jokes;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static String getResponseFromJokeApi() {
        return "{\n"
                + "    \"error\": false,\n"
                + "    \"amount\": 10,\n"
                + "    \"jokes\": [\n"
                + "        {\n"
                + "            \"category\": \"Pun\",\n"
                + "            \"type\": \"single\",\n"
                + "            \"joke\": \"Oysters hate to give away their pearls because they are shellfish.\",\n"
                + "            \"flags\": {\n"
                + "                \"nsfw\": false,\n"
                + "                \"religious\": false,\n"
                + "                \"political\": false,\n"
                + "                \"racist\": false,\n"
                + "                \"sexist\": false,\n"
                + "                \"explicit\": false\n"
                + "            },\n"
                + "            \"id\": 90,\n"
                + "            \"safe\": true,\n"
                + "            \"lang\": \"en\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"category\": \"Pun\",\n"
                + "            \"type\": \"single\",\n"
                + "            \"joke\": \"To whoever stole my copy of Microsoft Office, I will find you. You have my Word!\",\n"
                + "            \"flags\": {\n"
                + "                \"nsfw\": false,\n"
                + "                \"religious\": false,\n"
                + "                \"political\": false,\n"
                + "                \"racist\": false,\n"
                + "                \"sexist\": false,\n"
                + "                \"explicit\": false\n"
                + "            },\n"
                + "            \"id\": 191,\n"
                + "            \"safe\": true,\n"
                + "            \"lang\": \"en\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"category\": \"Misc\",\n"
                + "            \"type\": \"single\",\n"
                + "            \"joke\": \"Never date a baker. They're too kneady.\",\n"
                + "            \"flags\": {\n"
                + "                \"nsfw\": false,\n"
                + "                \"religious\": false,\n"
                + "                \"political\": false,\n"
                + "                \"racist\": false,\n"
                + "                \"sexist\": false,\n"
                + "                \"explicit\": false\n"
                + "            },\n"
                + "            \"safe\": true,\n"
                + "            \"id\": 271,\n"
                + "            \"lang\": \"en\"\n"
                + "        },\n"
                + " \n"
                + "    ]\n"
                + "}";
    }

    public static Jokes getJokes(boolean safe, boolean sexist, boolean explicit) {
        List<Joke> jokes = new ArrayList<Joke>();
        jokes.add(0, new Joke(1L,
                "Never date a baker. They're too kneady.",
                new Flag(sexist, explicit),
                safe));
        jokes.add(1, new Joke(2L,
                "To whoever stole my copy of Microsoft Office, I will find you. You have my Word!",
                new Flag(sexist, explicit),
                true));

        return new Jokes(false, 2L, jokes);
    }

    public static Joke getJoke() {
        return new Joke(1L,
                "Never date a baker. They're too kneady.",
                new Flag(false, false),
                true);
    }
}
