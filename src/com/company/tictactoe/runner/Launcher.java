package com.company.tictactoe.runner;

import com.company.tictactoe.elements.GameField;
import com.company.tictactoe.elements.players.Player;
import com.company.tictactoe.logic.Game;
import com.company.tictactoe.constants.Mode;
import com.company.tictactoe.logic.Settings;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;

        while (!isGameOver) {
            Settings settings = new Settings();
            Mode mode = settings.setMode();
            Player[] players = settings.createPlayers(mode);
            Game game = new Game();
            game.start(players, new GameField());

            System.out.print("\nTry again? (y / n): ");
            String answer = scanner.nextLine().toLowerCase();
            while (!answer.equals("y") && !answer.equals("n")) {
                System.out.print("Invalid value! Try again: ");
                answer = scanner.nextLine().toLowerCase();
            }
            if (answer.equals("n")) {
                isGameOver = true;
            }

            System.out.println();
        }
    }
}
