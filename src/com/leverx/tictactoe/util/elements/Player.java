package com.leverx.tictactoe.util.elements;

import com.leverx.tictactoe.util.logic.ElementType;

public class Player {
    private String name;
    private ElementType elementType;
    private boolean isComputer;

    public Player(String name, ElementType elementType, boolean isComputer) {
        this.name = name;
        this.elementType = elementType;
        this.isComputer = isComputer;
    }

    public String getName() {
        return name;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public boolean isComputer() {
        return isComputer;
    }
}
