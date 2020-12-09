package ru.diolloyd.javacore.lesson1;

public class Cat implements Actions {
    private final String name;
    private final int runPoint;
    private final int jumpPoint;

    public Cat(String name, int runPoint, int jumpPoint) {
        this.name = "Кот " + name;
        this.runPoint = runPoint;
        this.jumpPoint = jumpPoint;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRunPoint() {
        return runPoint;
    }

    @Override
    public int getJumpPoint() {
        return jumpPoint;
    }
}
