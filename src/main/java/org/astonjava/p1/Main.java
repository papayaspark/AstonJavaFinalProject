package org.astonjava.p1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {

  static Scanner scanner = new Scanner(System.in);
  static boolean failOnInvalid = true;

  public static void main(String[] args) {

    System.out.println("AstonJavaFinalProject 1.0 by Gang of Five, Inc.\n");

    boolean dataLoaded = false;
    ArrayList<ThreeMemberClass> data = null;

    String userInput = "";

    while (!userInput.equalsIgnoreCase("Q")) {

      userInput = getValidInputOption(
              "Выберите действие:\n"
                  + "1. Загрузка данных;\n"
                  + "2. Выбор способа сортировки;\n"
                  + "3. Сортировка данных;\n"
                  + "4. Поиск по данным;\n"
                  + "5. Запись данные в файл;\n"
                  + "Q. Выход.\n",
              Set.of("1", "2", "3", "4", "5", "Q"));

      System.out.println("Input: " + userInput);
      //
      //
      switch (userInput) {
        case "1":
          //
          System.out.println("data=" + data);
//          data = loadData();
          break;
        //        case "2":
        //          System.out.println("Выбор способа сортировки");
        //          break;
        //        case "3":
        //          System.out.println("Отсортированные данные");
        //          break;
        //        case "4":
        //          System.out.println("Поиск по данным");
        //          break;
        //        case "5":
        //          System.out.println("Запись в файл");
        //          break;
        case "q":
        case "Q":
          System.out.println("Выход");
          break;
      }
    }
  }

//  static ArrayList<ThreeMemberClass> loadData() {
//    Scanner scanner = new Scanner(System.in);
//    String userInput = "";
//    while (!userInput.equalsIgnoreCase("Q")) {
//      userInput = getValidInputOption(
//          "Выберите вариант загрузки данных:\n"
//              + "1. В интерактивном режиме\n"
//              + "2. Из файла\n"
//              + "3. Заполнение случайными данными\n"
//              + "Q. Выход\n", Set.of("1", "2", "3"));
//      userInput = scanner.nextLine();
//
//      switch (userInput) {
//        case "1":
//          break;
//        case "2":
//          break;
//        case "3":
//          break;
//        case "Q":
//          return null;
//      }
//    }
//
//    return null;
//  }

  // Выводит приветствие и ждет выбора корректного значения
  static String getValidInputOption(String greeting, Set<String> validOptions) {
    System.out.println(greeting);
    String inputString = scanner.nextLine();
    while (!validOptions.contains(inputString)) {
      System.out.println("Выбранного варианта нет в списке. Введите корректное значение");
      inputString = scanner.nextLine();
    }
    return inputString;
  }
}
