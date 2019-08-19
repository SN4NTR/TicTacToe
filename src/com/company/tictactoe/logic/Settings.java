package com.company.tictactoe.logic;

import com.company.tictactoe.constants.Difficulty;
import com.company.tictactoe.constants.ElementType;
import com.company.tictactoe.constants.Mode;
import com.company.tictactoe.elements.players.Bot;
import com.company.tictactoe.elements.players.Human;
import com.company.tictactoe.elements.players.Player;

import java.util.Scanner;

public class Settings {
    private Scanner scanner = new Scanner(System.in);

    public Mode setMode() {
        System.out.print("Choose game mode:\n" +
                "1. Singleplayer\n" +
                "2. Multiplayer\n" +
                "0. Quit\n" +
                "Your choice: "
        );

        int choice = makeChoice(0, 2);
        if (choice == 0) {
            System.exit(0);
        }

        if (choice == Mode.SINGLEPLAYER.getValue()) {
            return Mode.SINGLEPLAYER;
        } else {
            return Mode.MULTIPLAYER;
        }
    }

    public Player[] createPlayers(Mode mode) {
        final String BOT_NAME = "Ultralord";
        final int PLAYERS_NUMBER = 2;
        Player[] players = new Player[PLAYERS_NUMBER];

        if (mode == Mode.SINGLEPLAYER) {
            System.out.print("\nYour name: ");
            String name = scanner.nextLine();
            ElementType elementType = setElementType();
            if (elementType == ElementType.CROSS) {
                players[0] = new Human(name, elementType);
                players[1] = new Bot(BOT_NAME, ElementType.ZERO, setDifficulty());
            } else {
                players[0] = new Bot(BOT_NAME, ElementType.CROSS, setDifficulty());
                players[1] = new Human(name, elementType);
            }
        } else {
            System.out.print("\nFirst player name: ");
            String name1 = scanner.nextLine();
            ElementType elementType = setElementType();

            System.out.print("Second player name: ");
            String name2 = scanner.nextLine();

            if (elementType == ElementType.CROSS) {
                players[0] = new Human(name1, elementType);
                players[1] = new Human(name2, ElementType.ZERO);
            } else {
                players[0] = new Human(name2, ElementType.CROSS);
                players[1] = new Human(name1, elementType);
            }
        }

        System.out.println();

        return players;
    }

    private ElementType setElementType() {
        System.out.print("Choose your element type:\n" +
                "1. X\n" +
                "2. O\n" +
                "Your choice: "
        );

        int choice = makeChoice(1, 2);
        if (choice == ElementType.CROSS.getValue()) {
            return ElementType.CROSS;
        } else {
            return ElementType.ZERO;
        }
    }

    private Difficulty setDifficulty() {
        System.out.print("\nChoose game difficulty:\n" +
                "1. Easy\n" +
                "2. Hard\n" +
                "Your choice: "
        );

        int choice = makeChoice(1, 2);
        if (choice == Difficulty.EASY.getValue()) {
            return Difficulty.EASY;
        } else {
            return Difficulty.HARD;
        }
    }

    private int makeChoice(int minValue, int maxValue) {
        int choice = minValue - 1;

        while (choice < minValue || choice > maxValue) {
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < minValue || choice > maxValue) {
                System.out.print("Invalid value! Try again: ");
            }
        }

        return choice;
    }
}
