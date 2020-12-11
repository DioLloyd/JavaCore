package ru.diolloyd.javacore.lesson2;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int i, int j, String element) {
        super("В строке " + (i + 1) + " в ячейке " + (j + 1) + " находится не число, а " + element);
    }
}
