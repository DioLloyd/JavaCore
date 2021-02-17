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
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду\n1 - для получения погоды на текущий день\n" +
                    "2 - для получения погоды на пять дней\n" +
                    "3 - для получения архива погоды\n" +
                    "0 - для выхода");

            int selectedCommand = scanner.nextInt();
            scanner.nextLine();
            checkIsExit(selectedCommand);
            if (selectedCommand == 1 || selectedCommand == 2) {
                System.out.println("Введите название города на английском языке или 'exit' для выхода");

                String userResponse = scanner.nextLine();

                checkIsExit(userResponse);
                try {
                    controller.onCityInput(userResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            try {
                controller.onCommandChosen(selectedCommand);
            } catch (IOException e) {
                e.printStackTrace();
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

    private void checkIsExit(int userResponse) {
        if (userResponse == 0) {
            System.out.println("Завершаю работу...");
            System.exit(0);
        }
    }
}
