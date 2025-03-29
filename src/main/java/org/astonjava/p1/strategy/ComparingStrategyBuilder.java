package org.astonjava.p1.strategy;

import org.astonjava.p1.entities.Bus;
import org.astonjava.p1.entities.ThreeMemberClass;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// Реализует паттерн Builder для сравнения элементов при сортировке
public class ComparingStrategyBuilder<T extends ThreeMemberClass> {

  LinkedList<Comparator<T>> comparators = new LinkedList<>();

//  // TODO: Убрать, если другой окажется ОК
//  public ComparingStrategyBuilder<T> addComparator(Comparator<T> comparator, boolean reverse) {
//    if (reverse) comparators.add(comparator.reversed());
//    else comparators.add(comparator);
//    return this;
//  }

  public ComparingStrategyBuilder<T> addComparator(Comparator<T> comparator) {
    comparators.add(comparator);
    return this;
  }

  public Comparator<T> build() {
    if (comparators.isEmpty()) return null;
    Comparator<T> result = comparators.getFirst();
    for (int i = 1; i < comparators.size(); i++) {
      result = result.thenComparing(comparators.get(i));
    }
    return result;
  }

  public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
    Bus[] bus = {
      new Bus("100, KurganAuto, 2000"),
      new Bus("2, Mercedes, 100"),
      new Bus("1, SHL, 3434"),
      new Bus("100, KurganAuto, 2001"),
      new Bus("2, Mercedes, 100"),
      new Bus("1, SHL, 3434"),
      new Bus("100, KurganAuto, 2001"),
      new Bus("100, Mercedes, 2001"),
      new Bus("100, KurganAuto, 2002")
    };
//    System.out.println(Arrays.toString(Bus.class.getDeclaredMethods()));
//    Class<?> workingClass = Bus.class;
//    ComparingStrategyBuilder<Bus> builder = new ComparingStrategyBuilder<>();
//    builder
//        .addComparator((Comparator<Bus>) workingClass.getField("member1Comparator").get(null), false)
//        .addComparator(Bus.member2Comparator, true)
//        .addComparator(Bus.member3Comparator, false);
//    Comparator<Bus> comparator = builder.build();

    String[] order = new String[]{"1", "-2", "3"};
    Comparator<Bus> comparator = Bus.createSortingStrategy(order);

    Arrays.sort(bus, comparator);
//    ComparingStrategyBuilder<? extends ThreeMemberClass> builder = new ComparingStrategyBuilder<>();
//    Class<? extends ThreeMemberClass> workingClass = Bus.class;
//    try {
//      Comparator<? extends ThreeMemberClass> comp = (Comparator<ThreeMemberClass>) workingClass.getField("member1Comparator").get(null);
//      System.out.println(workingClass.getField("member1Comparator").get(null));
//      Arrays.sort(bus, comp);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

    for (var b : bus) System.out.println(b);
  }
}
