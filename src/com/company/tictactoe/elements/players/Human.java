package com.company.tictactoe.elements.players;

import com.company.tictactoe.elements.GameField;
import com.company.tictactoe.constants.ElementType;

import java.util.Scanner;

public class Human extends Player {

    public Human(String name, ElementType elementType) {
        super(name, elementType);
    }

    @Override
    public void makeStep(GameField gameField) {
        Scanner scanner = new Scanner(System.in);
        final int cellNumbersAmount = GameField.ROWS * GameField.COLUMNS;
        final int lowBorder = 1;
        int cellNumber = 0;

        while (cellNumber < lowBorder || cellNumber > cellNumbersAmount) {
            cellNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (cellNumber < lowBorder || cellNumber > cellNumbersAmount) {
                System.out.print("Invalid cell value! Try again: ");
            } else if (!gameField.isCellEmpty(cellNumber)) {
                System.out.print("Cell isn't empty! Try again: ");
                cellNumber = 0;
            }
        }

        gameField.setField(cellNumber, this.getElementType());
    }
}
