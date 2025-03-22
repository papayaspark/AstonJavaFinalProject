package org.astonjava.p1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("AstonJavaFinalProject 1.0 by Gang of Five, Inc.\n");

        boolean dataLoaded = false;
        ArrayList<ThreeFieldedClass> data;

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equalsIgnoreCase("Q")) {

            if (!dataLoaded) {
                System.out.println("Отсутствуют загруженные данные");
                // Загрузка данных
            }

            System.out.println("Выберите действие:");
            System.out.println("1. Загрузка данных;");
            System.out.println("2. Выбор способа сортировки;");
            System.out.println("3. Сортировка данных;");
            System.out.println("4. Поиск по данным;");
            System.out.println("Q. Выход");

            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    System.out.println("Выбрана загрузка данных");
                    dataLoaded = true;
                    break;
                case "2":
                    System.out.println("Выбор способа сортировки");
                    break;
                case "3":
                    System.out.println("Отсортированные данные");
                    break;
                case "4":
                    System.out.println("Поиск по данным");
                    break;
                case "q":
                case "Q":
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println("Выбранного варианта нет в списке. Введите корректное значение");
            }
        }
    }
}