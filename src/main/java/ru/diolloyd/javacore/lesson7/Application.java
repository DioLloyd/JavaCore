package ru.diolloyd.javacore.lesson7;

import ru.diolloyd.javacore.lesson7.view.IUserInterface;
import ru.diolloyd.javacore.lesson7.view.UserInterface;

public class Application {

    public static void main(String[] args) {
        IUserInterface ui = new UserInterface();
        ui.showMenu();
    }
}
