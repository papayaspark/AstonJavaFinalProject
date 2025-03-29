package org.astonjava.p1.collections;

import org.astonjava.p1.algorithms.BinarySearch;
import org.astonjava.p1.algorithms.QuickSort;
import org.astonjava.p1.entities.ThreeMemberClass;

import java.util.Arrays;
import java.util.Comparator;

public class CustomList<T extends ThreeMemberClass> implements Iterable<T> {
  private static final int DEFAULT_CAPACITY = 10;
  private T[] elements;
  private int size;

  @SuppressWarnings("unchecked")
  public CustomList() {
    this.elements = (T[]) new ThreeMemberClass[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public void add(T element) {
    ensureCapacity();
    elements[size++] = element;
  }

  public T get(int index) {
    checkIndex(index);
    return elements[index];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public T getFirst() {
    return elements[0];
  }

  // TODO: заменить на свои алгоритмы
  public void sort(Comparator<? super T> comparator) {
    QuickSort.quickSort(elements, 0, size - 1, comparator);
  }

  public int binarySearch(T key, Comparator<? super T> comparator) {
    return BinarySearch.search(elements, key, 0, size - 1, comparator);
  }

  private void ensureCapacity() {
    if (size == elements.length) {
      elements = Arrays.copyOf(elements, size * 2);
    }
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  @Override
  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<>() {
      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < size;
      }

      @Override
      public T next() {
        return elements[currentIndex++];
      }
    };
  }
}
