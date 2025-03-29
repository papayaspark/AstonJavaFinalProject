package org.astonjava.p1.algorithms;

import java.util.Arrays;
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

  // Для тестов
  public static void main(String[] args) {

    Integer[] array = new Integer[] {48, 76, 30, 50, 34, 48, 69, 72, 59, 47};
//    QuickSort.quickSort(array, 0, array.length - 1, Comparator.naturalOrder());
    Integer[] array2 = new Integer[] {48, 76, 30, 50, 34, 48, 69, 72, 59, 47};
    QuickSort.quickSort(array2, 0, array2.length - 1, Comparator.naturalOrder());
    System.out.println(Arrays.toString(array2));
    System.out.println(array2.length);


//    for (int i = 0; i < 10; i++) System.out.printf("%s %s\n", array[i], array2[i]);

    for (int i = 0; i < 10; i++)
      System.out.printf(
          "%s -> %s\n",
          array[i], search(array2 , array[i], 0 ,array2.length-1, Integer::compareTo));


//    System.out.println(
//            search(array2, 72, 0, array2.length - 1, Integer::compareTo));
  }

}
