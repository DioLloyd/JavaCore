package ru.diolloyd.javacore.lesson1;

public interface Actions {

    int getRunPoint();

    String getName();

    int getJumpPoint();

    default boolean running(int length) {
        if (getRunPoint() >= length) {
            System.out.println(getName() + " успешно пробежал " + length);
            return true;
        } else {
            System.out.println(getName() + " не смог пробежать " + length);
            return false;
        }
    }

    default boolean jumping(int height) {
        if (getJumpPoint() >= height) {
            System.out.println(getName() + " успешно прыгнул " + height);
            return true;
        } else {
            System.out.println(getName() + " не смог прыгнуть " + height);
            return false;
        }
    }
}
