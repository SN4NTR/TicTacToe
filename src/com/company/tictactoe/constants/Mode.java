package com.company.tictactoe.constants;

public enum Mode {
    SINGLEPLAYER(1),
    MULTIPLAYER(2);

    private int value;

    Mode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
