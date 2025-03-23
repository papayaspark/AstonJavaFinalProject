package org.astonjava.p1;

import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

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
      "Ram C/V Tradesman",
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
    return "%s, %s, %s".formatted(member1, member2, member3);
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
  //  public static void main(String[] args) {
  //    Bus bus = new Bus(1375, "KurganAuto", 45180);
  //    System.out.println(bus);
  //    System.out.println(Bus.validateString("1287, Sports1343_eire, 897777"));
  //    System.out.println(Bus.validateString("12kuy6.06, 9, 897777"));
  //    Bus bus2 = new Bus();
  //    System.out.println(bus2);
  //    System.out.println("------");
  //    for (int i = 0; i < 15; i++) {
  //      System.out.println(new Bus());
  //    }
  //  }
}
