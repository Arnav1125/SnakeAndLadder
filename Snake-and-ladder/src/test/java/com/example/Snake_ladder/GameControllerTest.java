package com.example.Snake_ladder;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Snake_ladder.model.Game;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/game";
    }

    @Test
    public void testJoin() {
        ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl + "/join?name=Player1", null, Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().get("playerId"));
    }

    @Test
    public void testStartGame() {
        restTemplate.postForEntity(baseUrl + "/start", null, Void.class);
        ResponseEntity<Game> response = restTemplate.getForEntity(baseUrl + "/state", Game.class);
        assertTrue(response.getBody().isGameStarted());
    }
}

