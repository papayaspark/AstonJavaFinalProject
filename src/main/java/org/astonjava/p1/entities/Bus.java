package org.astonjava.p1.entities;

import org.astonjava.p1.strategy.ComparingStrategyBuilder;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public class Bus extends ThreeMemberClass {

  static {
    validationPattern = Pattern.compile("^\\d{1,4},[\\w\\s]+,\\s*\\d{1,6}$");
  }

  public static Comparator<Bus> member1Comparator = Comparator.comparing(Bus::getMember1);
  public static Comparator<Bus> member2Comparator = Comparator.comparing(Bus::getMember2);
  public static Comparator<Bus> member3Comparator = Comparator.comparing(Bus::getMember3);

  public static String member1RuName = "Номер";
  public static String member2RuName = "Модель";
  public static String member3RuName = "Пробег";

  Integer member1;
  String member2;
  Integer member3;

//   TODO: Сделать здесь создание стратегии?
//    public static Comparator<Bus> buildStrategy(String[] sortingOrder) {
//      if (sortingOrder == null)
//        return null;
//      do {
//
//      }
//    }

  public Bus(Integer member1, String member2, Integer member3) {
    this.member1 = member1;
    this.member2 = member2;
    this.member3 = member3;
  }

  // Не проверяет данные! Использовать только после validateString
  public Bus(String validString) {
    String[] members = validString.split(",");
    member1 = Integer.parseInt(members[0].strip());
    member2 = members[1].strip();
    member3 = Integer.parseInt(members[2].strip());
  }

  public Bus() {
    Random rand = new Random();
    String[] models = {
      "Buick Terraza",
      "Chevrolet Express",
      "Chevrolet Venture",
      "Dodge Grand Caravan",
      "Dodge Ram Van B350",
      "Ford Econoline E150",
      "Ford Transit Connect",
      "GMC Safari",
      "GMC Savana",
      "Honda Odyssey",
      "Mercedes-Benz Sprinter",
      "Ram CV Tradesman",
      "Ram Promaster",
      "Toyota Sienna",
      "Volkswagen California"
    };
    this.member1 = rand.nextInt(9999);
    this.member2 = models[rand.nextInt(models.length)];
    this.member3 = rand.nextInt(999999);
  }

  @Override
  public String toString() {
    return "Bus {%s, %s, %s}".formatted(member1, member2, member3);
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

  // "Билдит" кастомный компаратор
  public static Comparator<Bus> createSortingStrategy(String[] sortingOrder) {
    ComparingStrategyBuilder<Bus> builder = new ComparingStrategyBuilder<>();
    Comparator<Bus> nextBlock = null;
    for (String s: sortingOrder) {
        nextBlock = switch (s) {
            case "1" -> member1Comparator;
            case "-1" -> member1Comparator.reversed();
            case "2" -> member2Comparator;
            case "-2" -> member2Comparator.reversed();
            case "3" -> member3Comparator;
            case "-3" -> member3Comparator.reversed();
            default -> nextBlock;
        };
      builder = builder.addComparator(nextBlock);
    }
    return builder.build();
  }

  // TODO: Убрать после тестов
  public static void main(String[] args) {
    Bus bus = new Bus(1375, "KurganAuto", 45180);
    System.out.println(bus);
    System.out.println(Bus.validateString("1287, Sports1343_eire, 897777"));
    System.out.println(Bus.validateString("12kuy6.06, 9, 897777"));
    Bus bus2 = new Bus();
    System.out.println(bus2);
    System.out.println("------");
    for (int i = 0; i < 15; i++) {
      System.out.println(new Bus());
    }

    System.out.println("------------------------");
    System.out.println(new Bus("5167, Pantsu Maru Mie, 614577"));
  }


}
