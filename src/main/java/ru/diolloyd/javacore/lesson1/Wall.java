package ru.diolloyd.javacore.lesson1;

public class Wall extends Obstructions {
    private final int heightWall;

    public Wall(int heightWall) {
        this.heightWall = heightWall;
    }

    @Override
    boolean overcome(Actions member) {
        return member.jumping(heightWall);
    }
}
