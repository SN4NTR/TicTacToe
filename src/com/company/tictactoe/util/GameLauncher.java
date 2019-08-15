package com.company.tictactoe.util;

import java.util.Scanner;

public class GameLauncher {
    private Scanner input = new Scanner(System.in);
    private GameField gameField = new GameField();
    private boolean running = true;

    public void start() {
        System.out.print(
                "1. Singleplayer;\n" +
                "2. Multiplayer;\n" +
                "0. Quit the game.\n\n" +
                "Your choice: "
        );

        int choice = -1;
        while (choice < 0 || choice > 2) {
            choice = input.nextInt();
            input.nextLine();
        }

        if (choice == GameMode.SINGLEPLAYER.getValue()) {
            singleplayer();
        } else if (choice == GameMode.MULTIPLAYER.getValue()) {
            multiplayer();
        }
    }

    private void singleplayer() {
        System.out.print("\nYour name: ");
        Player player = new Player(input.nextLine(), ElementType.CROSS);
        Player computer = new Player("Ultralord", ElementType.ZERO);

        gameField.displayField();

    }

    private void multiplayer() {
        System.out.print("\nFirst player name: ");
        Player player1 = new Player(input.nextLine(), ElementType.CROSS);
        System.out.print("Second player name: ");
        Player player2 = new Player(input.nextLine(), ElementType.ZERO);
        System.out.println();

        boolean playerSelector = false; // false - player1, true - player2

        while (running) {
            gameField.displayField();
            System.out.print("\n\n" + (!playerSelector ? player1.getName() : player2.getName()) +
                    ", enter cell number (1, 2, ..., 9): ");
            cellNumberEntering(!playerSelector ? player1.getElementType() : player2.getElementType());

            running = !isWinner();
            playerSelector = !playerSelector;

            if (gameField.isFieldFilled()) {
                break;
            }
        }

        gameField.displayField();
        if (!running && playerSelector) {
            System.out.println("\n\n" + player1.getName() + " is the winner!");
        } else if (!running && !playerSelector) {
            System.out.println("\n\n" + player2.getName() + " is the winner!");
        } else if (running) {
            System.out.println("\n\nDraw!");
        }

        running = true;
    }

    private void cellNumberEntering(ElementType elementType) {
        int cellNumber = 0;

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

        gameField.setField(cellNumber, elementType);
    }

    public boolean isWinner() {
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
