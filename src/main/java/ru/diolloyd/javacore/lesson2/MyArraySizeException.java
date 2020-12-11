package ru.diolloyd.javacore.lesson2;

public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super("Массив не 4х4");
    }
}
