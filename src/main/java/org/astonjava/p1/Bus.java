package org.astonjava.p1;

/**
 * TODO: Реализовать класс Автобус (Номер, Модель, Пробег) из задания.
 * Названия полей выберите сами.
 * Класс должен иметь стандартный конструктор по всем полям.
 * Класс должен иметь конструктор по умолчанию, который заполняет поля класса *валидными* случайными значениями.
 * Класс должен иметь сеттеры, геттеры, методы toString, equals и hashCode.
 *
 * Реализовать validateString() для своего класса.
 *
 * Кроме того, подготовить два файла: <имя_класса>_valid.csv и <имя_класса>_invalid.csv. Положить оба в папку
 * src/main/resources. В первом файле 15-20 строк вроде "Номер, Модель, Пробег" (без кавычек), все строки
 * хорошие (валидные). Во втором файле - плохие строки, количество на ваше усмотрение. Из этих файлов будут
 * считываться данные в главном цикле.
 */

public class Bus implements ThreeFieldedClass {
        public Bus (int mileage, String model, String number){
        this.mileage = mileage;
        this.model = model;
        this.number = number;
    }
    int mileage;
    String model;
    String number;
/*@Override
    public String toString(){
        return "Probeg" + mileage;
    }*/
    public int getMileage(){
        return mileage;
    }
    @Override
    public boolean validateString(String inputString) {
        return false;
    }
}
