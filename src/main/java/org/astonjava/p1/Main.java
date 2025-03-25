package org.astonjava.p1;

import java.util.*;

public class Main {

  static Scanner scanner = new Scanner(System.in);
  static boolean failOnInvalid = true;

  public static void main(String[] args) {

    System.out.println("AstonJavaFinalProject 1.0 by Gang of Five, Inc.\n");

    boolean dataLoaded = false;
    CustomList<ThreeMemberClass> data = null;

    String[] order;

    String userInput = "";

    while (!userInput.equalsIgnoreCase("Q")) {

      userInput =
          getValidInputOption(
              "Выберите действие:\n"
                  + "1. Загрузка данных;\n"
                  + "2. Выбор способа сортировки;\n"
                  + "3. Сортировка данных;\n"
                  + "4. Поиск по данным;\n"
                  + "5. Запись данные в файл;\n"
                  + "Q. Выход.\n",
              Set.of("1", "2", "3", "4", "5", "Q"));

      switch (userInput) {
        case "1":
          data = selectAndLoadData();
          break;
                  case "2":
                    order = getSortingOrder(Bus.class);
          System.out.println();
                    break;
          //        case "3":
          //          System.out.println("Отсортированные данные");
          //          break;
          //        case "4":
          //          System.out.println("Поиск по данным");
          //          break;
          //        case "5":
          //          System.out.println("Запись в файл");
          //          break;
      }

      printList(data);
      System.out.println("Выход");
    }
  }

  // Загружает данные с использованием паттерна Стратегия
  static CustomList<ThreeMemberClass> selectAndLoadData() {
    Map<String, Class<? extends ThreeMemberClass>> classMap =
        Map.of("1", Bus.class, "2", User.class, "3", Student.class);
    Map<String, DataLoaderInterface> loaderMap =
        Map.of(
            "1", new FileDataLoader(), "2", new ConsoleDataLoader(), "3", new RandomDataLoader());

    Scanner scanner = new Scanner(System.in);
    String classOption = "";
    String loaderOption = "";
    classOption =
        getValidInputOption(
            "Выберите класс:\n" + "1. Bus\n" + "2. User\n" + "3. Student\n" + "Q. Выход\n",
            Set.of("1", "2", "3", "Q"));
    if (classOption.equalsIgnoreCase("Q")) {
      System.out.println("Не выбран класс. Данные не загружены");
      return null;
    }
    loaderOption =
        getValidInputOption(
            "Выберите способ заполнения:\n"
                + "1. Из файла;\n"
                + "2. В интерактивном режиме;\n"
                + "3. Случайным образом;\n"
                + "Q. Выход\n",
            Set.of("1", "2", "3", "Q"));
    if (classOption.equalsIgnoreCase("q") || loaderOption.equalsIgnoreCase("q")) {
      System.out.println("Не выбран способ загрузки. Данные не загружены");
      return null;
    }

    return loaderMap.get(loaderOption).loadData(classMap.get(classOption));
  }

  // Выбирает порядок сортировки
  public static String[] getSortingOrder(Class<? extends ThreeMemberClass> tmClass) {
    try {
      Map<String, String> memberMap =
          Map.of(
              "1",
              (String) tmClass.getField("member1RuName").get(null),
              "2",
              (String) tmClass.getField("member2RuName").get(null),
              "3",
              (String) tmClass.getField("member3RuName").get(null),
              "-1",
              "-" + tmClass.getField("member1RuName").get(null),
              "-2",
              "-" + tmClass.getField("member2RuName").get(null),
              "-3",
              "-" + tmClass.getField("member3RuName").get(null));

      System.out.println(
          "Введите желаемый порядок сортировки\n"
              + "Формат: <наиболее приоритетное>, <с обычным приоритетом>, <наименее приоритетное>',\n"
              + "-<поле> для сортировки по этому полю в порядке убывания.\n"
              + "1. "
              + memberMap.get("1")
              + "\n"
              + "2. "
              + memberMap.get("2")
              + "\n"
              + "3. "
              + memberMap.get("3")
              + "\n");

      String first = getValidInputOption("Выберите наиболее приоритетное поле", Set.of("1", "-1", "2", "-2", "3", "-3"));
      String second = getValidInputOption("Выберите поле с обычным приоритетом", Set.of("1", "-1", "2", "-2", "3", "-3"));
      String third = getValidInputOption("Выберите наименее приоритетное поле", Set.of("1", "-1", "2", "-2", "3", "-3"));

      System.out.printf("Выбранный порядок сортировки: [%s, %s, %s]\n", memberMap.get(first), memberMap.get(second), memberMap.get(third));

      String[] result = new String[]{first, second, third};
      System.out.println("RESULT: " + Arrays.toString(result));

      return result;

    } catch (Exception e) {
      System.out.println("Ошибка в выборе порядка сортировки. Выбран порядок по умолчанию [1, 2, 3]");
//      e.printStackTrace();
      return new String[]{"1", "2", "3"};
    }
  }

  public static String[] getSortingOrderHelper() {

    return new String[0];
  }

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

  // Выводит в консоль содержимое списка
  static void printList(CustomList<? extends ThreeMemberClass> list) {
    if (list == null) {
      System.out.println("Список пуст");
    } else {
      for (var entry : list) {
        System.out.println(entry);
      }
    }
  }
}
