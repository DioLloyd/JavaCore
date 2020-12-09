package ru.diolloyd.javacore.lesson1;

public class Human implements Actions {
    private final String name;
    private final int runPoint;
    private final int jumpPoint;

    public Human(String name, int runPoint, int jumpPoint) {
        this.name = "Человек " + name;
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
