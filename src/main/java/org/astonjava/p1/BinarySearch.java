package org.astonjava.p1;

import java.util.Comparator;

public class BinarySearch {
    public static <T> int search(T[] sortedArray, T key, int low, int high, Comparator<T> comparator) {
    int index = Integer.MAX_VALUE;

    while (low <= high) {
        int mid = low  + ((high - low) / 2);
        if (comparator.compare(sortedArray[mid], key) < 0) {
            low = mid + 1;
        } else if (comparator.compare(sortedArray[mid], key) > 0) {
            high = mid - 1;
        } else if (comparator.compare(sortedArray[mid], key) == 0) {
            index = mid;
            break;
        }
    }
    return index;
}}
