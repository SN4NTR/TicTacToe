package com.company.tictactoe.util.elements;

import com.company.tictactoe.util.logic.ElementType;

public class Computer extends Player {

    public Computer(String name, ElementType elementType, boolean isComputer) {
        super(name, elementType, isComputer);
    }

    @Override
    public void enterCellNumber(GameField gameField) {
        int cellNumber = (int)(1 + Math.random() * 10);

        while(!gameField.isCellEmpty(cellNumber)) {
            cellNumber = (int)(1 + Math.random() * 10);
        }

        gameField.setField(cellNumber, this.getElementType());
    }
}
