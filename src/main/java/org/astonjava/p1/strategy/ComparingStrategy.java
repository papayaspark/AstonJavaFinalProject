package org.astonjava.p1.strategy;

import org.astonjava.p1.entities.ThreeMemberClass;

import java.util.Comparator;

public class ComparingStrategy<T> implements Comparator<T>{

    Comparator<T> comparator = null;

    ComparingStrategy(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int compare(T o1, T o2) {
        return comparator.compare(o1, o2);
    }

}
