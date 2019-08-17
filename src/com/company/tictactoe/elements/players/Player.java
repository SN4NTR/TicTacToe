package com.company.tictactoe.elements.players;

import com.company.tictactoe.elements.GameField;
import com.company.tictactoe.logic.ElementType;

public abstract class Player {
    private String name;
    private ElementType elementType;

    public Player(String name, ElementType elementType) {
        this.name = name;
        this.elementType = elementType;
    }

    public String getName() {
        return name;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public abstract void enterCellNumber(GameField gameField);
}
