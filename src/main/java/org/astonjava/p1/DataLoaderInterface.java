package org.astonjava.p1;

import java.util.ArrayList;

// Абстрактный класс загрузчика данных (из файла, из консоли или случайных)
public interface DataLoaderInterface {

  ArrayList<ThreeMemberClass> loadData();
}
