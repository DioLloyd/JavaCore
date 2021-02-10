package ru.diolloyd.javacore.lesson7.view;

import ru.diolloyd.javacore.lesson7.controller.Controller;
import ru.diolloyd.javacore.lesson7.controller.IController;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface implements IUserInterface {

    IController controller = new Controller();

    @Override
    public void showMenu() {

        while (true) {
            System.out.println("Введите название города на английском языке или 'exit' для выхода");
            Scanner scanner = new Scanner(System.in);

            String userResponse = scanner.nextLine();

            checkIsExit(userResponse);

            try{
                controller.onCityInput(userResponse);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            System.out.println("Введите команду\n1 - для получения погоды на текущий день\n2 - для получения погоды на пять дней");

            int selectedCommand = scanner.nextInt();

            try {
                controller.onCommandChosen(selectedCommand);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    private void checkIsExit(String userResponse) {
        if (userResponse.equalsIgnoreCase("exit") ||
        userResponse.equalsIgnoreCase("выход")) {
            System.out.println("Завершаю работу...");
            System.exit(0);
        }
    }
}
