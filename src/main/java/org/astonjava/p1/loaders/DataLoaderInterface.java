package org.astonjava.p1.loaders;

import org.astonjava.p1.collections.CustomList;
import org.astonjava.p1.entities.ThreeMemberClass;

// Абстрактный класс загрузчика данных (из файла, из консоли или случайных)
public interface DataLoaderInterface {

  CustomList<ThreeMemberClass> loadData(Class<? extends ThreeMemberClass> tmclass);
}
