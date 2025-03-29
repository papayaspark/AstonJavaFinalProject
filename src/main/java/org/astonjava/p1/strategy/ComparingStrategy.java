package org.astonjava.p1.strategy;

import org.astonjava.p1.entities.ThreeMemberClass;

import java.util.Comparator;

public class ComparingStrategy<T> {

    Comparator<T> comparator = null;

    public int compare(T o1, T o2) {
        return comparator.compare(o1, o2);
    }

}
