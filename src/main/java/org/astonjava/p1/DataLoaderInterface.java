package org.astonjava.p1;

// Абстрактный класс загрузчика данных (из файла, из консоли или случайных)
public interface DataLoaderInterface {

  CustomList<ThreeMemberClass> loadData(Class<? extends ThreeMemberClass> tmclass);
}
