package ru.diolloyd.javacore.lesson1;

public class Lesson1 {
    public static void main(String[] args) {

        Actions[] members = {
                new Human("Карл", 10, 3),
                new Cat("Барсик", 7, 5),
                new Robot("R2715", 20, 10)
        };

        Obstructions[] obstructions = {
                new RunningTrack(10),
                new Wall(5)
        };

        for (Actions member : members) {
            for (Obstructions obstruction : obstructions) {
                if (!obstruction.overcome(member)){
                    break;
                }
            }
        }
    }

}
