package com.leverx.tictactoe.util.logic;

import com.leverx.tictactoe.util.elements.GameField;
import com.leverx.tictactoe.util.elements.Player;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    private GameField gameField;

    public Game(GameField gameField) {
        this.gameField = gameField;
    }

    public void startGame(GameMode gameMode, Player... players) {
        boolean running = true;
        boolean playerSelector = false; // false - players[0], true - players[1]

        while(running) {
            gameField.displayField();

            if (!playerSelector) {
                System.out.print("\n\n" + players[0].getName() + ", enter cell number (1, 2, ..., 9): ");
            } else if (gameMode == GameMode.MULTIPLAYER) {
                System.out.print("\n\n" + players[1].getName() + ", enter cell number (1, 2, ..., 9): ");
            } else {
                System.out.println("\n");
            }

            if (!playerSelector) {
                cellNumberEntering(players[0]);
            } else {
                cellNumberEntering(players[1]);
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

    private void cellNumberEntering(Player player) {
        int cellNumber = 0;

        if (player.isComputer()) {
            cellNumber = (int)(1 + Math.random() * 10);

            while (!gameField.isCellEmpty(cellNumber)) {
                cellNumber = (int)(1 + Math.random() * 10);
            }
        } else {
            while (cellNumber < 1 || cellNumber > 9) {
                cellNumber = input.nextInt();
                input.nextLine();
                System.out.println();

                if (cellNumber < 1 || cellNumber > 9) {
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

            if (mainDiagonalCounter == GameField.ROWS) {
                return true;
            } else {
                mainDiagonalCounter = 0;
            }

            if (secondaryDiagonalCounter == GameField.ROWS) {
                return true;
            } else {
                secondaryDiagonalCounter = 0;
            }
        }

        return false;
    }
}
