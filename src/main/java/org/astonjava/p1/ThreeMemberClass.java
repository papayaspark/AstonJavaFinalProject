package org.astonjava.p1;

// Общий абстрактный класс для трех классов. Может измениться по ходу проекта.

import java.util.regex.Pattern;

public abstract class ThreeMemberClass {

  public static Pattern validationPattern;
  public static String member1RuName = "Поле 1";
  public static String member2RuName = "Поле 2";
  public static String member3RuName = "Поле 3";

  /* Проверяет, валидная ли строка или нет. */
  public static boolean validateString(String inputString) {
    return validationPattern.matcher(inputString).matches();
  }

  public abstract String toString();

  public abstract boolean equals(Object o);

  public abstract int hashCode();
}
