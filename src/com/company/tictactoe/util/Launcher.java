package com.company.tictactoe.util;

import com.company.tictactoe.util.elements.GameField;
import com.company.tictactoe.util.elements.Player;
import com.company.tictactoe.util.logic.ElementType;
import com.company.tictactoe.util.logic.Game;
import com.company.tictactoe.util.logic.GameMode;

import java.util.Scanner;

public class Launcher {
    private Scanner input = new Scanner(System.in);
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
            choice = input.nextInt();
            input.nextLine();

            if (choice < 0 || choice > 2) {
                System.out.print("Invalid value! Try again: ");
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
        Player[] players = new Player[PLAYERS_NUMBER];

        if (gameMode == GameMode.SINGLEPLAYER) {
            System.out.print("\nYour name: ");
            players[0] = new Player(input.nextLine(), ElementType.CROSS, false);
            players[1] = new Player("Ultralord", ElementType.ZERO, true);
            System.out.println();
        } else {
            System.out.print("\nFirst player name: ");
            players[0] = new Player(input.nextLine(), ElementType.CROSS, false);
            System.out.print("Second player name: ");
            players[1] = new Player(input.nextLine(), ElementType.ZERO, false);
            System.out.println();
        }

        game.startGame(gameMode, players[0], players[1]);
    }
}
