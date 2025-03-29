package org.astonjava.p1.strategy;

import org.astonjava.p1.entities.ThreeMemberClass;

import java.util.Comparator;
import java.util.LinkedList;

// Реализует паттерн Builder для сравнения элементов при сортировке
public class ComparingStrategyBuilder<T extends ThreeMemberClass> {

  LinkedList<Comparator<T>> comparators = new LinkedList<>();

  public ComparingStrategyBuilder<T> addComparator(Comparator<T> comparator) {
    comparators.add(comparator);
    return this;
  }

  public ComparingStrategy<T> build() {
    if (comparators.isEmpty()) return null;
    Comparator<T> result = comparators.getFirst();
    for (int i = 1; i < comparators.size(); i++) {
      result = result.thenComparing(comparators.get(i));
    }
    return new ComparingStrategy<T>(result);
  }
}
