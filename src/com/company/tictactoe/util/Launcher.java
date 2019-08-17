package com.company.tictactoe.util;

import com.company.tictactoe.util.elements.players.Computer;
import com.company.tictactoe.util.elements.GameField;
import com.company.tictactoe.util.elements.players.Human;
import com.company.tictactoe.util.elements.players.Player;
import com.company.tictactoe.util.logic.ElementType;
import com.company.tictactoe.util.logic.Game;
import com.company.tictactoe.util.logic.GameMode;

import java.util.Scanner;

public class Launcher {
    private Scanner scanner = new Scanner(System.in);
    private GameField gameField = new GameField();
    private Game game = new Game(gameField);

    public void chooseGameMode() {
        System.out.print(
                "1. Singleplayer;\n" +
                "2. Multiplayer;\n" +
                "0. Quit the game.\n\n" +
                "Your choice: "
        );

        int choice = -1;
        while (choice < 0 || choice > 2) {
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 0 || choice > 2) {
                System.out.print("Invalid value! Try again: ");
            } else if (choice == 0) {
                return;
            }
        }

        GameMode gameMode;
        if (choice == GameMode.SINGLEPLAYER.getValue()) {
            gameMode = GameMode.SINGLEPLAYER;
        } else {
            gameMode = GameMode.MULTIPLAYER;
        }

        gameSettings(gameMode);
    }

    private void gameSettings(GameMode gameMode) {
        final int PLAYERS_NUMBER = 2;
        final String BOT_NAME = "Ultralord";
        Player[] players = new Player[PLAYERS_NUMBER];

        if (gameMode == GameMode.SINGLEPLAYER) {
            System.out.print("\nYour name: ");
            String name = scanner.nextLine();
            ElementType elementType = chooseElementType();
            players[0] = new Human(name, elementType, false);

            if (elementType == ElementType.CROSS) {
                elementType = ElementType.ZERO;
            } else {
                elementType = ElementType.CROSS;
            }
            players[1] = new Computer(BOT_NAME, elementType, true);

            System.out.println();
        } else {
            System.out.print("\nFirst player name: ");
            String name = scanner.nextLine();
            ElementType elementType = chooseElementType();
            players[0] = new Human(name, elementType, false);

            System.out.print("Second player name: ");
            name = scanner.nextLine();
            if (elementType == ElementType.CROSS) {
                elementType = ElementType.ZERO;
            } else {
                elementType = ElementType.CROSS;
            }
            players[1] = new Human(name, elementType, false);

            System.out.println();
        }

        if (players[0].getElementType() == ElementType.CROSS) {
            game.startGame(players[0], players[1]);
        } else {
            game.startGame(players[1], players[0]);
        }
    }

    private ElementType chooseElementType() {
        System.out.print("Choose your element type:\n" +
                "1. X\n" +
                "2. O\n" +
                "Your choice: ");

        int number = 0;
        while (number < 1 || number > 2) {
            number = scanner.nextInt();
            scanner.nextLine();

            if (number < 1 || number > 2) {
                System.out.print("Invalid value! Try again: ");
            }
        }

        ElementType elementType;
        if (number == ElementType.CROSS.getValue()) {
            elementType = ElementType.CROSS;
        } else {
            elementType = ElementType.ZERO;
        }

        return elementType;
    }
}
