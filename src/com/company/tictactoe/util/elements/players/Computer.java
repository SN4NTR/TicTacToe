package com.company.tictactoe.util.elements.players;

import com.company.tictactoe.util.elements.GameField;
import com.company.tictactoe.util.logic.ElementType;
import com.company.tictactoe.util.logic.Game;

public class Computer extends Player {

    public Computer(String name, ElementType elementType, boolean isComputer) {
        super(name, elementType, isComputer);
    }

    @Override
    public void enterCellNumber(GameField gameField) {
        int[][] field = new int[GameField.ROWS][GameField.COLUMNS];
        for (int i = 0; i < field.length; i++) {
            field[i] = gameField.getField()[i].clone();
        }

        final int ROWS_CENTER = GameField.ROWS / 2;
        final int COLUMNS_CENTER = GameField.COLUMNS / 2;
        int cellNumber = 0;

        boolean isGameEndings = false;

        int tempCellNumber = checkWin(gameField, this.getElementType());
        if (tempCellNumber > 0) {
            cellNumber = tempCellNumber;
            isGameEndings = true;
        }

        if (!isGameEndings) {
            if (this.getElementType() == ElementType.CROSS) {
                tempCellNumber = checkWin(gameField, ElementType.ZERO);
            } else {
                tempCellNumber = checkWin(gameField, ElementType.CROSS);
            }
            if (tempCellNumber > 0) {
                cellNumber = tempCellNumber;
                isGameEndings = true;
            }
        }

        if (!isGameEndings) {
            if (field[ROWS_CENTER][COLUMNS_CENTER] == ElementType.EMPTY.getValue()) {
                cellNumber = COLUMNS_CENTER + ROWS_CENTER * GameField.COLUMNS + 1;
            } else {
                if (field[0][0] == ElementType.EMPTY.getValue()) {
                    cellNumber = 1;
                } else if (field[0][GameField.COLUMNS - 1] == ElementType.EMPTY.getValue()) {
                    cellNumber = GameField.COLUMNS;
                } else if (field[GameField.ROWS - 1][0] == ElementType.EMPTY.getValue()) {
                    cellNumber = (GameField.ROWS - 1) * GameField.COLUMNS + 1;
                } else if (field[GameField.ROWS - 1][GameField.COLUMNS - 1] == ElementType.EMPTY.getValue()) {
                    cellNumber = GameField.COLUMNS + (GameField.ROWS - 1) * GameField.COLUMNS;
                } else {
                    int i = 1;

                    while (!gameField.isCellEmpty(i)) {
                        i++;
                    }

                    cellNumber = i;
                }
            }
        }

        gameField.setField(cellNumber, this.getElementType());
    }

    private int checkWin(GameField gameField, ElementType elementType) {
        int[][] field = new int[GameField.ROWS][GameField.COLUMNS];
        for (int i = 0; i < field.length; i++) {
            field[i] = gameField.getField()[i].clone();
        }

        int cellNumber = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int tempCellNumber = j + i * GameField.COLUMNS + 1;

                if (gameField.isCellEmpty(tempCellNumber)) {
                    field[i][j] = elementType.getValue();

                    if (Game.isWinner(field)) {
                        field[i][j] = ElementType.EMPTY.getValue();
                        i = field.length;
                        cellNumber = tempCellNumber;
                        break;
                    }

                    field[i][j] = ElementType.EMPTY.getValue();
                }
            }
        }

        return cellNumber;
    }
}
