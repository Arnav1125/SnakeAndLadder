package com.example.Snake_ladder.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Snake_ladder.model.Game;
import com.example.Snake_ladder.model.Player;
import com.example.Snake_ladder.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/join")
    public Map<String, String> join(@RequestParam String name) {
        String playerId = UUID.randomUUID().toString();
        gameService.addPlayer(name);
        Map<String, String> response = new HashMap<>();
        response.put("playerId", playerId);
        return response;
    }

    @PostMapping("/leave")
    public void leave(@RequestParam String playerId) {
        gameService.removePlayer(playerId);
    }

    @GetMapping("/state")
    public Game getState() {
        return gameService.getGameState();
    }

    @PostMapping("/start")
    public void start() {
        gameService.startGame();
    }

    @PostMapping("/reset")
    public void reset() {
        gameService.resetGame();
    }

    @PostMapping("/roll")
    public Map<String, Object> roll(@RequestParam String playerId) {
        gameService.rollDice(playerId);
        Game game = gameService.getGameState();
        Player currentPlayer = game.getCurrentPlayer();
        Map<String, Object> response = new HashMap<>();
        response.put("playerId", playerId);
        response.put("diceValue", game.rollDice());
        response.put("newPosition", currentPlayer.getPosition());
        game.nextPlayer();
        return response;
    }
}
