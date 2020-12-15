package ru.diolloyd.javacore.lesson3;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson3 {
    public static void main(String[] args) {
        Object[] testArray = {123, "borsch", 0.15f, 24, "Java"};
        System.out.println(Arrays.toString(testArray));
        changePosition(0, 4, testArray);
        System.out.println(Arrays.toString(testArray));

        Box<Apple> appleBox = new Box<>(new ArrayList<>());
        Box<Orange> orangeBox = new Box<>(new ArrayList<>());
        Box<Apple> appleBox1 = new Box<>(new ArrayList<>());
        Box<Orange> orangeBox1 = new Box<>(new ArrayList<>());
        Box<Fruit> box = new Box<>(new ArrayList<>());
        for (int i = 0; i < 9; i++) {
            appleBox.addFruit(new Apple());
        }
        for (int i = 0; i < 7; i++) {
            orangeBox.addFruit(new Orange());
        }
        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(appleBox1.getWeight());
        System.out.println(orangeBox1.getWeight());
        System.out.println(appleBox.compare(orangeBox));
        appleBox.interlard(appleBox1);
        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(appleBox1.getWeight());
        System.out.println(orangeBox1.getWeight());
    }


    public static <T> void changePosition(int firstElement, int secondElement, T[] array) {
        if (firstElement >= 0 && firstElement < array.length && secondElement >= 0 && secondElement < array.length) {
            T temporary = array[firstElement];
            array[firstElement] = array[secondElement];
            array[secondElement] = temporary;
        } else {
            System.out.println("Индексы массива неверные");
        }
    }
}
