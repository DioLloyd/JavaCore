package ru.diolloyd.javacore.lesson1;

public class Robot implements Actions {
    private final String name;
    private final int runPoint;
    private final int jumpPoint;

    public Robot(String name, int runPoint, int jumpPoint) {
        this.name = "Робот " + name;
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
