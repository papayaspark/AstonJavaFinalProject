package org.astonjava.p1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    System.out.println("AstonJavaFinalProject 1.0 by Gang of Five, Inc.\n");

    boolean dataLoaded = false;
    ArrayList<ThreeMemberClass> data;

    Scanner scanner = new Scanner(System.in);
    String userInput = "";

    while (!userInput.equalsIgnoreCase("Q")) {

      System.out.println(
          "Выберите действие:\n"
              + "1. Загрузка данных;\n"
              + "2. Выбор способа сортировки;\n"
              + "3. Сортировка данных;\n"
              + "4. Поиск по данным;\n"
              + "5. Запись данные в файл;\n"
              + "Q. Выход.\n");

      userInput = scanner.nextLine();

      switch (userInput) {
        case "1":
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
        case "5":
          System.out.println("Запись в файл");
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
