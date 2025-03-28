package org.astonjava.p1;

import org.astonjava.p1.collections.CustomList;
import org.astonjava.p1.entities.Bus;
import org.astonjava.p1.entities.Student;
import org.astonjava.p1.entities.ThreeMemberClass;
import org.astonjava.p1.entities.User;
import org.astonjava.p1.loaders.ConsoleDataLoader;
import org.astonjava.p1.loaders.DataLoaderInterface;
import org.astonjava.p1.loaders.FileDataLoader;
import org.astonjava.p1.loaders.RandomDataLoader;

import java.util.*;

public class Main {

  static Scanner scanner = new Scanner(System.in);

  static CustomList<ThreeMemberClass> data = null;
  static Comparator<? extends ThreeMemberClass> sortingStrategy = null;
  static boolean sorted = false;
  static Map<String, Class<? extends ThreeMemberClass>> classMap =
      Map.of("1", Bus.class, "2", User.class, "3", Student.class);

  static String classOption = null;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    System.out.println("AstonJavaFinalProject 1.0 by Gang of Five, Inc.");

    String[] order;

    String userInput = "";

    while (!userInput.equalsIgnoreCase("Q")) {

      userInput =
          getValidInputOption(
              """

                          Выберите действие:
                          1. Загрузка данных;
                          2. Выбор способа сортировки;
                          3. Сортировка данных;
                          4. Поиск по данным;
                          5. Вывод данных в консоль;
                          Q. Выход.""",
              Set.of("1", "2", "3", "4", "5", "Q", "q"));

      switch (userInput) {
        case "1":
          data = selectAndLoadData();
          sortingStrategy = null;
          sorted = false;
          break;
        case "2":
          System.out.println();
          switch (classOption) {
            case "1" -> {
              order = getSortingOrder(Bus.class);
              sortingStrategy = Bus.createSortingStrategy(order);
            }
            case "2" -> {
              order = getSortingOrder(User.class);
              sortingStrategy = User.createSortingStrategy(order);
            }
            case "3" -> {
              order = getSortingOrder(Student.class);
              sortingStrategy = Student.createSortingStrategy(order);
            }
          }
          break;
        case "3":
          if (data == null) {
            System.out.println("Данные не загружены");
            break;
          } else if (sortingStrategy == null) {
            System.out.println("Не выбрана стратегия сортировки");
            break;
          } else {
            System.out.println("\nОтсортированные данные");
            data.sort((Comparator<ThreeMemberClass>) sortingStrategy);
            sorted = true;
            printList(data);
            System.out.println();
          }
          System.out.println();
          break;
        case "4":
          System.out.println("\nПоиск по данным");
          if (data == null) {
            System.out.println("Данные не загружены");
            break;
          } else if (!sorted) {
            System.out.println("Данные не отсортированы");
            break;
          }
          ThreeMemberClass key;
          try {
            System.out.printf(
                "Введите данные в следующем формате '%s, %s, %s':%n",
                classMap.get(classOption).getField("member1RuName").get(null),
                classMap.get(classOption).getField("member2RuName").get(null),
                classMap.get(classOption).getField("member3RuName").get(null));
            key = ConsoleDataLoader.checkAndCreate(classMap.get(classOption), scanner.nextLine());
          } catch (Exception e) {
            key = null;
          }
          if (key == null) {
            System.out.println("Неверный формат записи");
          } else {

            int position = data.binarySearch(key, (Comparator<ThreeMemberClass>) sortingStrategy);
            if (position == -1) {
              System.out.println("Запись не найдена");
            } else {
              System.out.printf("Позиция искомой записи: %d%n", position + 1);
            }
          }
          break;
        case "5":
          System.out.println("\nСодержимое data:");
          printList(data);
          break;
        case "q":
        case "Q":
          System.out.println("\nВыход");
          break;
      }
    }
  }

  // Загружает данные с использованием паттерна Стратегия
  static CustomList<ThreeMemberClass> selectAndLoadData() {

    Map<String, DataLoaderInterface> loaderMap =
        Map.of(
            "1", new FileDataLoader(), "2", new ConsoleDataLoader(), "3", new RandomDataLoader());

    String loaderOption;
    classOption =
        getValidInputOption(
            """

                        Выберите класс:
                        1. Bus
                        2. User
                        3. Student
                        Q. Выход""",
            Set.of("1", "2", "3", "Q"));
    if (classOption.equalsIgnoreCase("Q")) {
      System.out.println("Не выбран класс. Данные не загружены");
      return null;
    }
    loaderOption =
        getValidInputOption(
            """

                        Выберите способ заполнения:
                        1. Из файла;
                        2. В интерактивном режиме;
                        3. Случайным образом;
                        Q. Выход""",
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
          "\nВведите желаемый порядок сортировки (-<поле> для сортировки по этому полю в порядке убывания):\n"
              + "1. "
              + memberMap.get("1")
              + "\n"
              + "2. "
              + memberMap.get("2")
              + "\n"
              + "3. "
              + memberMap.get("3")
              + "\n");

      String first =
          getValidInputOption(
              "Выберите наиболее приоритетное поле", Set.of("1", "-1", "2", "-2", "3", "-3"));
      String second =
          getValidInputOption(
              "Выберите поле с обычным приоритетом", Set.of("1", "-1", "2", "-2", "3", "-3"));
      String third =
          getValidInputOption(
              "Выберите наименее приоритетное поле", Set.of("1", "-1", "2", "-2", "3", "-3"));

      System.out.printf(
          "Выбранный порядок сортировки: [%s, %s, %s]\n",
          memberMap.get(first), memberMap.get(second), memberMap.get(third));

      return new String[] {first, second, third};

    } catch (Exception e) {
      System.out.println(
          "Ошибка в выборе порядка сортировки. Выбран порядок по умолчанию [Поле_1, Поле_2, Поле_3]");
      return new String[] {"1", "2", "3"};
    }
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
      for (int i = 0; i < list.size(); i++) {
        System.out.printf("%d. %s%n", i + 1, list.get(i));
      }
    }
  }
}
