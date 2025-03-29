package org.astonjava.p1.loaders;

import org.astonjava.p1.collections.CustomList;
import org.astonjava.p1.entities.Bus;
import org.astonjava.p1.entities.ThreeMemberClass;

import java.lang.reflect.Constructor;
import java.util.Scanner;

// Возвращает заполненный случайными данными CustomList
public class RandomDataLoader implements DataLoaderInterface {
  @Override
  public CustomList<ThreeMemberClass> loadData(Class<? extends ThreeMemberClass> tmclass) {

    try {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Введите нужное количество записей: ");
      int length = scanner.nextInt();

      CustomList<ThreeMemberClass> result = new CustomList<>();

      Class<?>[] constructorArgs = new Class[] {};
      Constructor<? extends ThreeMemberClass> tmConstructor =
          tmclass.getDeclaredConstructor(constructorArgs);

      for (int i = 0; i < length; i++) result.add(tmConstructor.newInstance());

      return result;

    } catch (Exception e) {
      return null;
    }
  }

  // TODO: Убрать после тестов
  public static void main(String[] args) {
    RandomDataLoader randomDataLoader = new RandomDataLoader();

    CustomList<ThreeMemberClass> result = randomDataLoader.loadData(Bus.class);

    if (result != null) {
      for (var d : result) {
        System.out.println(d);
      }
    } else {
      System.out.println(result);
    }
  }
}
