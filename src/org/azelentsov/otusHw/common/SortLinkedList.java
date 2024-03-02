package org.azelentsov.otusHw.common;

public class SortLinkedList {


    public Integer value;

    public SortLinkedList next;

    public SortLinkedList(int element, SortLinkedList previousList) {
        value = element;
        next = previousList;
    }
}
