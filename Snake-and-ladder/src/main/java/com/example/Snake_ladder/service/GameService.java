package com.example.Snake_ladder.service;

import org.springframework.stereotype.Service;

import com.example.Snake_ladder.model.Game;
import com.example.Snake_ladder.model.Player;

@Service
public class GameService {
    private Game game = new Game();

    public void addPlayer(String name) {
        Player player = new Player(name);
        game.addPlayer(player);
    }

    public void removePlayer(String playerId) {
        game.removePlayer(playerId);
    }

    public Game getGameState() {
        return game;
    }

    public void startGame() {
        game.startGame();
    }

    public void resetGame() {
        game.resetGame();
    }

    public void rollDice(String playerId) {
        Player currentPlayer = game.getCurrentPlayer();
        if (!currentPlayer.getId().equals(playerId)) {
            throw new IllegalStateException("Not your turn");
        }

        int diceValue = game.rollDice();
        game.movePlayer(currentPlayer, diceValue);
        game.nextPlayer();
    }
}
