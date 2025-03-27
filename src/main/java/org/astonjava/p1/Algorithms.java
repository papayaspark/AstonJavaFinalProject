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
        int border = array.get(middle).mileage;
        //разделияем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (array.get(i).mileage < border) i++;
            while (array.get(j).mileage > border) j--;
            if (i <= j) {
                int swap = array.get(i).mileage;
                array.get(i).mileage = array.get(j).mileage;
                array.get(j).mileage = swap;
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
