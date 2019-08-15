package com.company.tictactoe.util;

import java.util.Scanner;

public class GameLauncher {
    private Scanner input = new Scanner(System.in);
    private GameField gameField = new GameField();
    private boolean running = true;

    public void menu() {
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
        System.out.println();

        startGame(GameMode.SINGLEPLAYER, player, computer);
    }

    private void multiplayer() {
        System.out.print("\nFirst player name: ");
        Player player1 = new Player(input.nextLine(), ElementType.CROSS);
        System.out.print("Second player name: ");
        Player player2 = new Player(input.nextLine(), ElementType.ZERO);
        System.out.println();

        startGame(GameMode.MULTIPLAYER, player1, player2);
    }

    private void startGame(GameMode gameMode, Player ... players) {
        boolean playerSelector = false;

        while(running) {
            gameField.displayField();

            if (!playerSelector) {
                System.out.print("\n\n" + players[0].getName() +
                        ", enter cell number (1, 2, ..., 9): ");
            } else if (gameMode == GameMode.MULTIPLAYER) {
                System.out.print("\n\n" + players[1].getName() +
                        ", enter cell number (1, 2, ..., 9): ");
            } else {
                System.out.println("\n");
            }

            if (!playerSelector) {
                cellNumberEntering(players[0].getElementType());
            } else if (gameMode == GameMode.MULTIPLAYER) {
                cellNumberEntering(players[1].getElementType());
            } else {
                calculating(players[1].getElementType());
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

    private void calculating(ElementType elementType) {
        int cellNumber = (int)(1 + Math.random() * 10);

        while (!gameField.isCellEmpty(cellNumber)) {
            cellNumber = (int)(1 + Math.random() * 10);
        }

        gameField.setField(cellNumber, elementType);
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
