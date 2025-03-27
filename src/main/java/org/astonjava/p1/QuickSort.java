package org.astonjava.p1;

import java.util.Comparator;

public class QuickSort {

  public static <T> void quickSort(T[] arr, int low, int high, Comparator<T> comparator) {
    if (low < high) {
      int pi = partition(arr, low, high, comparator);

      quickSort(arr, low, pi - 1, comparator);
      quickSort(arr, pi + 1, high, comparator);
    }
  }

  private static <T> int partition(T[] arr, int low, int high, Comparator<T> comparator) {
    T pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (comparator.compare(arr[j], pivot) < 0) {
        i++;

        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    T temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
  }
}
