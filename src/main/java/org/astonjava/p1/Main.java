package org.astonjava.p1;
import java.util.LinkedList;

public class Main extends Bus {
    static LinkedList<Bus> arrayBus = new LinkedList<>();

    public Main(int mileage, String model, String number) {
        super(mileage, model, number);
    }

    public static void main(String[] args) {
        Bus bus1 = new Bus(3000, "mersedez S 500", "AE 200 Y");
        Bus bus2 = new Bus(40000, "porshe A 2", "OU 344 S");
        Bus bus3 = new Bus(200000, "lada 14", "MK 200 K");
        Bus bus4 = new Bus(100, "ford focus", "BN 341 I");
        Bus bus5 = new Bus(50030, "ferrari enzo", "IU 100 B");
        arrayBus.add(bus1);
        arrayBus.add(bus2);
        arrayBus.add(bus3);
        arrayBus.add(bus4);
        arrayBus.add(bus5);
        for(int i = 0; i < 5; i++) {
            System.out.println("Пробег: " + arrayBus.get(i).getMileage());
        }
        quickSort(arrayBus, 0, 4);
        for(int i = 0; i < 5; i++) {
            System.out.println("Пробег: " + arrayBus.get(i).getMileage());
        }
    }
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
}
