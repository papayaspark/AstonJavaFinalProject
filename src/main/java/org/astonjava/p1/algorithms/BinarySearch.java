package org.astonjava.p1.algorithms;

import java.util.Comparator;

// Реализует бинарный поиск
public class BinarySearch {
  public static <T> int search(
      T[] sortedArray, T key, int low, int high, Comparator<T> comparator) {

    //    System.out.println("Got low=" + low + "\nGot high=" + high);
    while (low <= high) {
      //      System.out.printf("%s %s\n", low, high);
      int mid = (high + low) / 2;
      if (comparator.compare(sortedArray[mid], key) < 0) {
        low = mid + 1;
      } else if (comparator.compare(sortedArray[mid], key) > 0) {
        high = mid - 1;
      } else if (comparator.compare(sortedArray[mid], key) == 0) {
        return mid;
      }
    }
    return -1;
  }
}
