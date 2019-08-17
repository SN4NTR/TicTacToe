package com.company.tictactoe.util.elements;

import com.company.tictactoe.util.logic.ElementType;

public abstract class Player {
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

    public abstract void enterCellNumber(GameField gameField);
}
