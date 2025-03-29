package org.astonjava.p1.loaders;

import org.astonjava.p1.collections.CustomList;
import org.astonjava.p1.entities.Bus;
import org.astonjava.p1.entities.ThreeMemberClass;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// Загружает данные из файла
public class FileDataLoader implements DataLoaderInterface {

  // Основной метод, выполняющий загрузку
  @Override
  public CustomList<ThreeMemberClass> loadData(Class<? extends ThreeMemberClass> tmclass) {

    System.out.print("Введите путь к файлу: ");
    String pathToFile = (new Scanner(System.in)).nextLine();

    CustomList<ThreeMemberClass> result = new CustomList<>();
    int lines_processed = 0;

    Path path = Paths.get(pathToFile);

    try {
      for (var s : Files.readAllLines(path)) {
        lines_processed++;
        ThreeMemberClass record = checkAndCreate(tmclass, s);
        //        System.out.printf("s = %s, record = %s\n", s, record);
        if (record != null) {
          result.add(record);
          //          System.out.println(result);
        }
      }
      System.out.printf(
          "Всего записей в файле: %s, загружено записей: %s, записей с ошибками: %s\n",
          lines_processed, result.size(), lines_processed - result.size());
      return result;

    } catch (IOException e) {
      System.out.println("Не удалось считать данные. Проверьте правильность пути к файлу");
      return null;
    } catch (Exception e) {
      System.out.println("Ошибка в FileDataLoader.loadData()");
      return null;
    }
  }

  // Проверяет строку и создает объект, если строка плохая, возвращает null
  public static ThreeMemberClass checkAndCreate(
      Class<? extends ThreeMemberClass> tmclass, String dataString) {
    try {
      Class<?>[] constructorArgs = new Class[] {String.class};
      Constructor<? extends ThreeMemberClass> tmConstructor = tmclass.getDeclaredConstructor(constructorArgs);
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

    public static void main(String[] args) {
//      System.out.println("Result: " + checkAndCreate(Bus.class, "120hgfg6, Chevrolet Express, 58325"));
//      System.out.println("Result: " + checkAndCreate(Bus.class, "60, Buick Terraza,"));
      FileDataLoader loader = new FileDataLoader();
      CustomList<ThreeMemberClass> data = loader.loadData(Bus.class);
      for (var d : data) {
        System.out.println(d);
      }
    }
}
