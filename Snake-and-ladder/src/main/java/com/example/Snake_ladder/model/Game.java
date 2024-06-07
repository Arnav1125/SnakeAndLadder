package com.example.Snake_ladder.model;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private boolean isGameStarted;

    public Game() {
        this.players = new ArrayList<>();
        this.board = new Board();
        this.currentPlayerIndex = 0;
        this.isGameStarted = false;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(String playerId) {
        players.removeIf(player -> player.getId().equals(playerId));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void startGame() {
        isGameStarted = true;
    }

    public void resetGame() {
        players.forEach(player -> player.setPosition(0));
        currentPlayerIndex = 0;
        isGameStarted = false;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public void movePlayer(Player player, int steps) {
        int newPosition = player.getPosition() + steps;

        if (newPosition > board.getSize()) {
            return; // Player does not move if roll exceeds board size
        }

        if (board.getSnakes().containsKey(newPosition)) {
            newPosition = board.getSnakes().get(newPosition);
        } else if (board.getLadders().containsKey(newPosition)) {
            newPosition = board.getLadders().get(newPosition);
        }

        player.setPosition(newPosition);
    }
}
