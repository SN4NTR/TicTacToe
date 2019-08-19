package com.company.tictactoe.constants;

public enum Difficulty {
    EASY(1),
    HARD(2);

    int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
