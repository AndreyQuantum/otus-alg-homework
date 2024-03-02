package org.azelentsov.otusHw.common;

import java.util.List;

public class SortLinkedList {


    public Integer value;

    public SortLinkedList nextValue;

    public SortLinkedList(int element, SortLinkedList previousList) {
        value = element;
        nextValue = previousList;
    }
}
