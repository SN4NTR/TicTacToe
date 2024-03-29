package com.company.tictactoe.elements;

import com.company.tictactoe.constants.ElementType;

public class GameField {
    public static final int COLUMNS = 3;
    public static final int ROWS = 3;
    private int[][] field = new int[ROWS][COLUMNS];

    public int[][] getField() {
        return field;
    }

    public void setField(int cellNumber, ElementType type) {
        int[] indices = findElementIndices(cellNumber);
        int row = indices[0];
        int column = indices[1];
        field[row][column] = type.getValue();
    }

    public boolean isCellEmpty(int cellNumber) {
        int[] indices = findElementIndices(cellNumber);
        int row = indices[0];
        int column = indices[1];

        return field[row][column] == ElementType.EMPTY.getValue();
    }

    public boolean isFull() {
        for (int[] value : field) {
            for (int i : value) {
                if (i == ElementType.EMPTY.getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void displayField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == ElementType.EMPTY.getValue()) {
                    System.out.print(" ");
                } else if (field[i][j] == ElementType.CROSS.getValue()) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }

                if (j != field[i].length - 1) {
                    System.out.print(" | ");
                }
            }

            if (i != field.length - 1) {
                System.out.println("\n---------");
            }
        }
    }

    private int[] findElementIndices(int cellNumber) {
        int counter = 0;
        int[] result = new int[2];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                counter++;

                if (counter == cellNumber) {
                    result[0] = i;
                    result[1] = j;
                    i = field.length;
                    break;
                }
            }
        }

        return result;
    }
}
