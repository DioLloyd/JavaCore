package ru.diolloyd.javacore.lesson3;

import java.util.ArrayList;
import java.util.Collection;

public class Box<T extends Fruit> {

    private final ArrayList<T> fruits;

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public int quantity() {
        return this.fruits.size();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        if (fruits.isEmpty()) {
            return 0;
        }
        return this.quantity() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<?> box){
        return this.getWeight() == box.getWeight();
    }

    public void interlard (Box<T> box) {
        for (T fruit : fruits) {
            box.addFruit(fruit);
        }
        fruits.clear();
    }
}
