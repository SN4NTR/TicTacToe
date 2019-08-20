package com.company.tictactoe.logic;

import com.company.tictactoe.constants.ElementType;
import com.company.tictactoe.elements.GameField;
import com.company.tictactoe.elements.players.Player;

public class Game {

    public void start(Player[] players, GameField gameField) {
        boolean isWinner;
        boolean playerSelector = false; // false - players[0], true - players[1]

        while (true) {
            gameField.displayField();

            if (!playerSelector) {
                System.out.print("\n\n" + players[0].getName() + ", enter cell number (1, 2, ..., 9): ");
                players[0].makeStep(gameField);
            } else {
                System.out.print("\n\n" + players[1].getName() + ", enter cell number (1, 2, ..., 9): ");
                players[1].makeStep(gameField);
            }

            isWinner = checkWin(gameField.getField());
            if (isWinner) {
                break;
            } else if (gameField.isFull()) {
                break;
            }

            playerSelector = !playerSelector;
        }

        gameField.displayField();
        if (!playerSelector && isWinner) {
            System.out.println("\n\n" + players[0].getName() + " is the winner!");
        } else if (playerSelector && isWinner) {
            System.out.println("\n\n" + players[1].getName() + " is the winner!");
        } else {
            System.out.println("\n\nDraw!");
        }
    }

    public boolean checkWin(int[][] field) {
        int rowCounter = 0;
        int columnCounter = 0;
        int mainDiagonalCounter = 0;
        int secondaryDiagonalCounter = 0;

        for (int i = 0; i < field.length; i++) {
            int tempRow = field[i][0];
            int tempColumn = field[0][i];
            int tempMainDiagonal = field[0][0];
            int tempSecondaryDiagonal = field[0][GameField.COLUMNS - 1];
            int k = field[i].length - 1;

            for (int j = 0; j < field[i].length; j++) {
                if (tempRow != ElementType.EMPTY.getValue() && field[i][j] == tempRow) {
                    rowCounter++;
                }
                if (tempColumn != ElementType.EMPTY.getValue() && field[j][i] == tempColumn) {
                    columnCounter++;
                }
                if (tempMainDiagonal != ElementType.EMPTY.getValue() && field[j][j] == tempMainDiagonal) {
                    mainDiagonalCounter++;
                }

                if (tempSecondaryDiagonal != ElementType.EMPTY.getValue()) {
                    if (field[j][k] == tempSecondaryDiagonal) {
                        secondaryDiagonalCounter++;
                        k--;
                    }
                }
            }

            if (rowCounter == GameField.ROWS) {
                return true;
            } else {
                rowCounter = 0;
            }

            if (columnCounter == GameField.COLUMNS) {
                return true;
            } else {
                columnCounter = 0;
            }

            if (mainDiagonalCounter == Math.sqrt(GameField.ROWS * GameField.COLUMNS)) {
                return true;
            } else {
                mainDiagonalCounter = 0;
            }

            if (secondaryDiagonalCounter == Math.sqrt(GameField.ROWS * GameField.COLUMNS)) {
                return true;
            } else {
                secondaryDiagonalCounter = 0;
            }
        }

        return false;
    }
}
