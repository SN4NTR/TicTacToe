package com.company.tictactoe.util;

public class Player {
    private String name;
    private ElementType elementType;

    Player(String name, ElementType elementType) {
        this.name = name;
        this.elementType = elementType;
    }

    public String getName() {
        return name;
    }

    public ElementType getElementType() {
        return elementType;
    }
}
