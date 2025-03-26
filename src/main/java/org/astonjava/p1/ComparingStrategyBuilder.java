package org.astonjava.p1;

import java.util.Arrays;
import java.util.Comparator;

// Реализует паттерн Builder для сравнения элементов при сортировке
public class ComparingStrategyBuilder<T extends ThreeMemberClass> {

  CustomList<Comparator<T>> comparators = new CustomList<>();

  public ComparingStrategyBuilder<T> addComparator(Comparator<T> comparator, boolean reverse) {
    if (reverse) comparators.add(comparator.reversed());
    else comparators.add(comparator);
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

  public static void main(String[] args) {
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
    System.out.println(Arrays.toString(Bus.class.getDeclaredMethods()));

    ComparingStrategyBuilder<Bus> builder = new ComparingStrategyBuilder<>();
    builder
        .addComparator(Bus.member1Comparator, true)
        .addComparator(Bus.member2Comparator, false)
        .addComparator(Bus.member3Comparator, false);
    Comparator<Bus> comparator = builder.build();

    Arrays.sort(bus, comparator);

    for (var b : bus) System.out.println(b);
  }
}
