package org.azelentsov.otusHw.task05Arrays.src.model;

import java.util.Arrays;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray () {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    @Override
    public void add(T item, int index) {
        Object[] newArray = new Object[size()+1];
        System.arraycopy(array, 0, newArray, 0,size()-index);
        newArray[index] = item;
        System.arraycopy(array, index, newArray, index+1,size()-index);
        System.out.println(newArray);
        array=newArray;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Object[] newArray = new Object[size()-1];
        T oldElement = (T) array[index];
        System.arraycopy(array, 0, newArray, 0,size()-index+1);
        System.arraycopy(array, index,newArray,index-1,size()-index);
        array = newArray;
        return  oldElement;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i<size(); i++){
            builder.append(array[i]);
            if (i != size()-1){
                builder.append(" ,");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    private void resize() {
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
//        for (int j = 0; j < size(); j ++)
//            newArray[j] = array[j];
        array = newArray;
    }
}
