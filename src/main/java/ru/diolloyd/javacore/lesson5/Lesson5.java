package ru.diolloyd.javacore.lesson5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lesson5 {

    public static final String DELIMITER = ";";
    public static final Pattern PATTERN = Pattern.compile("(\\n)|;");

    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = {{100, 200, 300}, {500, 600, 700}};
        AppData appData = new AppData(header, data);

        writeToFile(file, appData);

        AppData appData1 = readFromFile(file);

        printAppData(appData1);
    }

    private static void printAppData(AppData appData) {
        for (int i = 0; i < appData.getHeader().length; i++) {
            System.out.printf("%9s", appData.getHeader()[i]);
        }
        System.out.println();
        for (int[] line : appData.getData()) {
            for (int j : line) {
                System.out.printf("%9d", j);
            }
            System.out.println();
        }
    }

    private static AppData readFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter(PATTERN);
            String line = scanner.nextLine();
            String[] header1 = line.split(DELIMITER);
            List<int[]> list = new ArrayList<>();
            while (scanner.hasNext()) {
                int[] tempArr = new int[header1.length];
                for (int j = 0; j < header1.length; j++) {
                    tempArr[j] = scanner.nextInt();
                }
                list.add(tempArr);
            }
            int[][] data1 = list.toArray(new int[list.size()][]);
            return new AppData(header1, data1);
        }
    }

    private static void writeToFile(File file, AppData appData) {
        try (DataOutputStream fileOutput = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            fileOutput.write(String.join(DELIMITER, appData.getHeader())
                    .getBytes(StandardCharsets.UTF_8));
            for (int[] line : appData.getData()) {
                fileOutput.writeBytes("\n");
                for (int i = 0; i < line.length; i++) {
                    String num = String.valueOf(line[i]);
                    fileOutput.writeBytes(num);
                    if (i < line.length - 1) {
                        fileOutput.writeBytes(DELIMITER);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
