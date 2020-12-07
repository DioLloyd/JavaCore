package ru.diolloyd.javacore.lesson1;

public class RunningTrack extends Obstructions {
    private final int trackLength;

    public RunningTrack(int trackLength) {
        this.trackLength = trackLength;
    }

    @Override
    boolean overcome(Actions member) {
        return member.running(trackLength);
    }

}
