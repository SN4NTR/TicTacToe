package com.company.tictactoe.util;

import java.util.Scanner;

public class GameLauncher {
    private Scanner input = new Scanner(System.in);
    private GameField gameField = new GameField();
    private boolean running = true;

    public void start() {
        System.out.print("1. Singleplayer;\n" +
                "2. Multiplayer;\n" +
                "0. Quit the game.\n\n" +
                "Your choice: ");

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
        Player player = new Player();
        System.out.print("\nYour name: ");
        player.setName(input.nextLine());

        gameField.displayField();
    }

    private void multiplayer() {
        Player player1 = new Player();
        Player player2 = new Player();
        System.out.print("\nFirst player name: ");
        player1.setName(input.nextLine());
        System.out.print("Second player name: ");
        player2.setName(input.nextLine());
        System.out.println();

        int roundCounter = 1;
        while (running) {
            gameField.displayField();
            int cellNumber = 0;

            while (cellNumber < 1 || cellNumber > 9) {
                System.out.print("\n\n" + (roundCounter % 2 == 0 ? player2.getName() : player1.getName()) +
                        ", enter cell number (1, 2, ..., 9): ");
                cellNumber = input.nextInt();
                input.nextLine();
                System.out.println();

                if (cellNumber < 1 || cellNumber > 9) {
                    System.out.print("Invalid cell value!");
                } else if (!gameField.isCellEmpty(cellNumber)) {
                    System.out.print("Cell isn't empty!");
                    cellNumber = 0;
                }
            }

            if (roundCounter % 2 == 0) {
                gameField.setField(cellNumber, ElementType.ZERO);
            } else {
                gameField.setField(cellNumber, ElementType.CROSS);
            }

            running = !isGameOver(cellNumber);
            roundCounter++;

            if (roundCounter - 1 == GameField.ROWS * GameField.COLUMNS) {
                break;
            }
        }

        gameField.displayField();
        if (!running && roundCounter % 2 == 0) {
            System.out.println("\n\n" + player1.getName() + " is the winner!");
        } else if (!running && roundCounter % 2 == 1) {
            System.out.println("\n\n" + player2.getName() + " is the winner!");
        } else if (running) {
            System.out.println("\n\nDraw!");
        }
    }

    public boolean isGameOver(int cellNumber) {
        int[] indices = gameField.findElementIndices(cellNumber);
        int row = indices[0];
        int column = indices[1];
        int[][] field = gameField.getField();

        int tempRow = row - row % field.length;
        if (field[tempRow][column] == field[tempRow + 1][column] &&
                field[tempRow][column] == field[tempRow + 2][column]) {
            return true;
        }

        int tempColumn = column - column % field.length;
        if (field[row][tempColumn] == field[row][tempColumn + 1] &&
                field[row][tempColumn] == field[row][tempColumn + 2]) {
            return true;
        }

        if (cellNumber % 2 == 0) {
            return false;
        } else {
            tempRow = tempColumn = 0;

            if (cellNumber % 5 == 0) {
                if (field[tempRow][tempColumn] == field[tempRow + 1][tempColumn + 1] &&
                        field[tempRow][tempColumn] == field[tempRow + 2][tempColumn + 2]) {
                    return true;
                }
                if (field[tempRow][tempColumn + 2] == field[tempRow + 1][tempColumn + 1] &&
                        field[tempRow][tempColumn + 2] == field[tempRow + 2][tempColumn]) {
                    return true;
                }

                return false;
            } else {
                if (cellNumber == 1 || cellNumber == 9) {
                    return field[tempRow][tempColumn] == field[tempRow + 1][tempColumn + 1] &&
                            field[tempRow][tempColumn] == field[tempRow + 2][tempColumn + 2];
                }

                return field[tempRow][tempColumn + 2] == field[tempRow + 1][tempColumn + 1] &&
                        field[tempRow][tempColumn + 2] == field[tempRow + 2][tempColumn];
            }
        }
    }
}
