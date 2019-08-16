package com.company.tictactoe.util.logic;

import com.company.tictactoe.util.elements.GameField;
import com.company.tictactoe.util.elements.Player;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    private GameField gameField;

    public Game(GameField gameField) {
        this.gameField = gameField;
    }

    public void startGame(Player... players) {
        boolean running = true;
        boolean playerSelector = false; // false - players[0], true - players[1]

        while (running) {
            gameField.displayField();

            if (!playerSelector) {
                if (!players[0].isComputer()) {
                    System.out.print("\n\n" + players[0].getName() + ", enter cell number (1, 2, ..., 9): ");
                } else {
                    System.out.println("\n");
                }
            } else {
                if (!players[1].isComputer()) {
                    System.out.print("\n\n" + players[1].getName() + ", enter cell number (1, 2, ..., 9): ");
                } else {
                    System.out.println("\n");
                }
            }

            if (!playerSelector) {
                enterCellNumber(players[0]);
            } else {
                enterCellNumber(players[1]);
            }

            running = !isWinner();
            playerSelector = !playerSelector;

            if (gameField.isFieldFilled()) {
                break;
            }
        }

        gameField.displayField();
        if (!running && playerSelector) {
            System.out.println("\n\n" + players[0].getName() + " is the winner!");
        } else if (!playerSelector) {
            System.out.println("\n\n" + players[1].getName() + " is the winner!");
        } else {
            System.out.println("\n\nDraw!");
        }
    }

    private void enterCellNumber(Player player) {
        final int cellNumbersAmount = GameField.ROWS * GameField.COLUMNS;
        final int lowBorder = 1;
        int cellNumber = 0;

        if (player.isComputer()) {
            cellNumber = (int) (lowBorder + Math.random() * (cellNumbersAmount + 1));

            while (!gameField.isCellEmpty(cellNumber)) {
                cellNumber = (int) (lowBorder + Math.random() * (cellNumbersAmount + 1));
            }
        } else {
            while (cellNumber < lowBorder || cellNumber > cellNumbersAmount) {
                cellNumber = input.nextInt();
                input.nextLine();
                System.out.println();

                if (cellNumber < lowBorder || cellNumber > cellNumbersAmount) {
                    System.out.print("Invalid cell value! Try again: ");
                } else if (!gameField.isCellEmpty(cellNumber)) {
                    System.out.print("Cell isn't empty! Try again: ");
                    cellNumber = 0;
                }
            }
        }

        gameField.setField(cellNumber, player.getElementType());
    }

    private boolean isWinner() {
        int[][] field = gameField.getField();
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
