package org.astonjava.p1;

import java.util.Comparator;

public class Algorithms {

    // TODO: Вынести сюда быструю сортировку
    // Пока можно считать CustomList обычным LinkedList
    //public static void quickSort(CustomList<ThreeMemberClass> list) {}
public static void quickSort(LinkedList<Bus>array, int low, int high) {
        //завершить,если массив пуст или уже нечего делить
        if (array.size() == 0 || low >= high) return;

        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        int border = array.get(middle).member3;
        //разделияем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (array.get(i).member3 < border) i++;
            while (array.get(j).member3 > border) j--;
            if (i <= j) {
                int swap = array.get(i).member3;
                array.get(i).member3 = array.get(j).member3;
                array.get(j).member3 = swap;
                i++;
                j--;
            }
        }
        //рекурсия для сортировки левой и правой части
        if (low < j) quickSort(array, low, j);
        if (high > i) quickSort(array, i, high);
    }
    public static Integer binarySearch(CustomList<ThreeMemberClass> list) {
        return 0;
    }
}
