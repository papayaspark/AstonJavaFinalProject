package org.astonjava.p1;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * TODO: Реализовать класс Автобус (Номер, Модель, Пробег) из задания. Названия полей выберите сами.
 * Класс должен иметь конструктор по
 * умолчанию, который заполняет поля класса *валидными* случайными значениями.
 *
 * <p>Кроме того, подготовить два файла: <имя_класса>_valid.csv и <имя_класса>_invalid.csv. Положить
 * оба в папку src/main/resources. В первом файле 15-20 строк вроде "Номер, Модель, Пробег" (без
 * кавычек), все строки хорошие (валидные). Во втором файле - плохие строки, количество на ваше
 * усмотрение. Из этих файлов будут считываться данные в главном цикле.
 */

public class Bus extends ThreeMemberClass {

  static {
    validationPattern = Pattern.compile("^\\d{1,4},[\\w\\s]+,\\s*\\d{1,6}$");
    member1RuName = "Номер";
    member2RuName = "Модель";
    member3RuName = "Пробег";
  }

  Integer member1;
  String member2;
  Integer member3;

  public Bus(Integer member1, String member2, Integer member3) {
    this.member1 = member1;
    this.member2 = member2;
    this.member3 = member3;
  }

  @Override
  public String toString() {
    return "Bus{%s, %s, %s}".formatted(member1, member2, member3);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Bus bus = (Bus) o;
    return Objects.equals(member1, bus.member1)
        && Objects.equals(member2, bus.member2)
        && Objects.equals(member3, bus.member3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(member1, member2, member3);
  }

  public Integer getMember1() {
    return member1;
  }

  public void setMember1(Integer member1) {
    this.member1 = member1;
  }

  public String getMember2() {
    return member2;
  }

  public void setMember2(String member2) {
    this.member2 = member2;
  }

  public Integer getMember3() {
    return member3;
  }

  public void setMember3(Integer member3) {
    this.member3 = member3;
  }

  // TODO: Убрать после тестов
  public static void main(String[] args) {
    Bus bus = new Bus(1375, "KurganAuto", 45180);
    System.out.println(bus);
    System.out.println(Bus.validateString("1287, Sports1343_eire, 897777"));
    System.out.println(Bus.validateString("12kuy6.06, 9, 897777"));
  }
}
