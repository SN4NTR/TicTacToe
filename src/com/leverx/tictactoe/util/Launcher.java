package com.leverx.tictactoe.util;

import com.leverx.tictactoe.util.elements.GameField;
import com.leverx.tictactoe.util.elements.Player;
import com.leverx.tictactoe.util.logic.ElementType;
import com.leverx.tictactoe.util.logic.Game;
import com.leverx.tictactoe.util.logic.GameMode;

import java.util.Scanner;

public class Launcher {
    private Scanner input = new Scanner(System.in);
    private GameField gameField = new GameField();
    private Game game = new Game(gameField);

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
        Player player = new Player(input.nextLine(), ElementType.CROSS, false);
        Player computer = new Player("Ultralord", ElementType.ZERO, true);
        System.out.println();

        game.startGame(GameMode.SINGLEPLAYER, player, computer);
    }

    private void multiplayer() {
        System.out.print("\nFirst player name: ");
        Player player1 = new Player(input.nextLine(), ElementType.CROSS, false);
        System.out.print("Second player name: ");
        Player player2 = new Player(input.nextLine(), ElementType.ZERO, false);
        System.out.println();

        game.startGame(GameMode.MULTIPLAYER, player1, player2);
    }
}
