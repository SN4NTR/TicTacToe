package com.company.tictactoe.logic;

public enum ElementType {
    CROSS(1),
    ZERO(2),
    EMPTY(0);

    private int value;

    ElementType(int value) {
        this.value = value;
    }

    public int getValue() {
       return value;
    }
}
