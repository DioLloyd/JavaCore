package ru.diolloyd.javacore.lesson1;

public class Wall implements Obstructions {
    private final int heightWall;

    public Wall(int heightWall) {
        this.heightWall = heightWall;
    }

    @Override
    public boolean overcome(Actions member) {
        return member.jumping(heightWall);
    }
}
