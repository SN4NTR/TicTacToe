package com.company.tictactoe.util;

public enum GameMode {
    SINGLEPLAYER(1),
    MULTIPLAYER(2);

    private int value;

    GameMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
