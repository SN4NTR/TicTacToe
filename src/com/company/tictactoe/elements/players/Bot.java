package com.company.tictactoe.elements.players;

import com.company.tictactoe.elements.GameField;
import com.company.tictactoe.constants.ElementType;
import com.company.tictactoe.constants.Difficulty;
import com.company.tictactoe.logic.Game;

public class Bot extends Player {
    private Difficulty difficulty;

    public Bot(String name, ElementType elementType, Difficulty difficulty) {
        super(name, elementType);
        this.difficulty = difficulty;
    }

    @Override
    public void makeStep(GameField gameField) {
        int cellNumber = 0;

        if (this.difficulty == Difficulty.EASY) {
            cellNumber = (int)(1 + Math.random() * GameField.ROWS * GameField.COLUMNS);

            while (!gameField.isCellEmpty(cellNumber)) {
                cellNumber = (int)(1 + Math.random() * GameField.ROWS * GameField.COLUMNS);
            }
        } else {
            int[][] field = new int[GameField.ROWS][GameField.COLUMNS];
            for (int i = 0; i < field.length; i++) {
                field[i] = gameField.getField()[i].clone();
            }

            final int ROWS_CENTER = GameField.ROWS / 2;
            final int COLUMNS_CENTER = GameField.COLUMNS / 2;

            boolean isGameOver = false;

            int tempCellNumber = analyzeStep(gameField, this.getElementType());
            if (tempCellNumber > 0) {
                cellNumber = tempCellNumber;
                isGameOver = true;
            }

            if (!isGameOver) {
                if (this.getElementType() == ElementType.CROSS) {
                    tempCellNumber = analyzeStep(gameField, ElementType.ZERO);
                } else {
                    tempCellNumber = analyzeStep(gameField, ElementType.CROSS);
                }
                if (tempCellNumber > 0) {
                    cellNumber = tempCellNumber;
                    isGameOver = true;
                }
            }

            if (!isGameOver) {
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
        }

        gameField.setField(cellNumber, this.getElementType());
        System.out.println(cellNumber + "\n");
    }

    private int analyzeStep(GameField gameField, ElementType elementType) {
        int[][] field = new int[GameField.ROWS][GameField.COLUMNS];
        for (int i = 0; i < field.length; i++) {
            field[i] = gameField.getField()[i].clone();
        }

        Game game = new Game();
        int cellNumber = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int tempCellNumber = j + i * GameField.COLUMNS + 1;

                if (gameField.isCellEmpty(tempCellNumber)) {
                    field[i][j] = elementType.getValue();

                    if (game.checkWin(field)) {
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
