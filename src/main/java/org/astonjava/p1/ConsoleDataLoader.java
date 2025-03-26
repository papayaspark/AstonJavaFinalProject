package org.astonjava.p1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

// Загружает данные из консоли в интерактивном режиме
public class ConsoleDataLoader implements DataLoaderInterface {
  @Override
  public CustomList<ThreeMemberClass> loadData(Class<? extends ThreeMemberClass> tmclass) {
    try {
      Scanner scanner = new Scanner(System.in);
      System.out.printf(
          "Введите данные в следующем формате '%s, %s, %s', ('Q' для выхода):\n",
          tmclass.getField("member1RuName").get(null),
          tmclass.getField("member2RuName").get(null),
          tmclass.getField("member3RuName").get(null));

      CustomList<ThreeMemberClass> result = new CustomList<>();

      String inputString = scanner.nextLine();

      while (!inputString.equalsIgnoreCase("Q")) {
        ThreeMemberClass record = checkAndCreate(tmclass, inputString);
        if (record != null) {
          result.add(record);
        }
        inputString = scanner.nextLine();
      }

      System.out.printf("Введено корректных записей %s\n", result.size());
      return result;

    } catch (Exception e) {
      return null;
    }
  }

  // Проверяет строку и создает объект, если строка плохая, возвращает null
  public static ThreeMemberClass checkAndCreate(
      Class<? extends ThreeMemberClass> tmclass, String dataString) {
    try {
      Class<?>[] constructorArgs = new Class[] {String.class};
      Constructor<? extends ThreeMemberClass> tmConstructor =
          tmclass.getDeclaredConstructor(constructorArgs);
      Method tmValidator = tmclass.getMethod("validateString", String.class);
      tmConstructor.newInstance("1, JOJO, 1"); // MAGIC STRING!!!

      if ((Boolean) tmValidator.invoke(null, dataString)) {
        return tmConstructor.newInstance(dataString);
      }
    } catch (Exception e) {
      //      System.out.println("Не получилось создать объект в checkAndCreate()");
      return null;
    }
    return null;
  }

  // TODO: Убрать после тестов
  public static void main(String[] args) {
    DataLoaderInterface loader = new ConsoleDataLoader();

    CustomList<ThreeMemberClass> result;

    result = loader.loadData(Bus.class);

    for (var d : result) {
      System.out.println(d);
    }
  }
}
