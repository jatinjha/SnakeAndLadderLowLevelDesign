package com.company.model;

import java.util.UUID;

public class Player {
    private final String playerId;
    private String playerName;
    private boolean isWinner;

    public Player(String playerName) {
        this.playerId = UUID.randomUUID().toString();
        this.playerName = playerName;
        this.isWinner = false;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
