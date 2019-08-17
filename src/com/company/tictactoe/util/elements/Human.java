package com.company.tictactoe.util.elements;

import com.company.tictactoe.util.logic.ElementType;

import java.util.Scanner;

public class Human extends Player {

    public Human(String name, ElementType elementType, boolean isComputer) {
        super(name, elementType, isComputer);
    }

    @Override
    public void enterCellNumber(GameField gameField) {
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
