package ru.diolloyd.javacore.lesson2;

import java.util.Arrays;

public class Lesson2 {
    public static void main(String[] args) {
        String[][][] myArrays = {
                createArray(4, 4),
                createArray(4, 4),
                createArray(4, 5)
        };
        myArrays[1][2][2] = "ДВА";

        for (String[][] myArray : myArrays) {
            printArray(myArray);
            calculate(myArray);
            System.out.println();
        }
    }

    private static void calculate(String[][] myArray) {
        try {
            System.out.println("Сумма элементов массива " + myMethod(myArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int myMethod(String[][] myArray) throws MyArrayDataException, MyArraySizeException {
        int sum = 0;
        if (myArray.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < myArray[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(myArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, myArray[i][j]);
                }
            }
        }
        return sum;
    }

    private static String[][] createArray(int rows, int cols) {
        String[][] myArray = new String[rows][cols];
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                myArray[i][j] = String.valueOf(j);
            }
        }
        return myArray;
    }

    private static void printArray(String[][] myArray) {
        for (String[] strings : myArray) {
            System.out.println(Arrays.toString(strings));
        }
    }
}
