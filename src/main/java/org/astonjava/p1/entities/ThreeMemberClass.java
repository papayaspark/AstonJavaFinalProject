package org.astonjava.p1.entities;

// Общий абстрактный класс для трех классов. Может измениться по ходу проекта.

import java.util.regex.Pattern;

public abstract class ThreeMemberClass {

  public static Pattern validationPattern;

  /* Проверяет, валидная ли строка или нет. */
  public static boolean validateString(String inputString) {
    return validationPattern.matcher(inputString).matches();
  }

  public abstract String toString();

  public abstract boolean equals(Object o);

  public abstract int hashCode();
}
