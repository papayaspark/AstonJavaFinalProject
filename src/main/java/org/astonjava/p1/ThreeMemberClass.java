package org.astonjava.p1;

// Общий абстрактный класс для трех классов. Может измениться по ходу проекта.

import java.util.regex.Pattern;

public abstract class ThreeMemberClass {

  static Pattern validdtionPattern;

  String member1RuName = "Поле 1";
  String member2RuName = "Поле 2";
  String member3RuName = "Поле 3";

  /* Проверяет, валидная ли строка или нет. */
  public static boolean validateString(String inputString) {
    return validdtionPattern.matcher(inputString).matches();
  }

  public abstract String toString();

  public abstract boolean equals();

  public abstract int hashCode();
}
