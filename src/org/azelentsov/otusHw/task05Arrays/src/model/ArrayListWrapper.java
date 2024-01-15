package org.azelentsov.otusHw.task05Arrays.src.model;

import java.util.ArrayList;
import java.util.List;

public class ArrayListWrapper<T> implements IArray<T>{

    private final List<T> list = new ArrayList<>();
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void add(T item, int index) {
        list.add(index, item);
    }

    @Override
    public T remove(int index) {
        return list.remove(index);
    }
}
