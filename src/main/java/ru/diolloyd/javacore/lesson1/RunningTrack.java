package ru.diolloyd.javacore.lesson1;

public class RunningTrack implements Obstructions {
    private final int trackLength;

    public RunningTrack(int trackLength) {
        this.trackLength = trackLength;
    }

    @Override
    public boolean overcome(Actions member) {
        return member.running(trackLength);
    }

}
