package org.azelentsov.otusHw.task05Arrays.src.model;

public class VectorArray<T> implements IArray<T> {

    private Object[] array;
    private int vector;
    private int size;

//    public String toString(){
//        StringBuilder builder = new StringBuilder();
//        builder.append('[');
//        for (int i = 0; i<size(); i++){
//            builder.append(array[i]);
//            if (i != size()-1){
//                builder.append(" ,");
//            }
//        }
//        builder.append(']');
//        return builder.toString();
//    }

    @Override
    public T remove(int index) {
        T deletedElement = (T) array[index];
        for (int i = index; i< size()-1; i++){
            array[i] = array[i+1];
        }
        size--;
        if (size()-1 == array.length - vector - 1){
            Object[] newArray = new Object[array.length -vector];
            System.arraycopy(array, 0, newArray, 0, array.length-vector);
            array = newArray;
        }
        return deletedElement;
    }

    public VectorArray(int vector) {
        this.vector = vector;
        array = new Object[0];
        size = 0;
    }


    public VectorArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    @Override
    public void add(T item, int index) {
        if (size() == array.length)
            resize();
        for (int i = size(); i > index; i--){
            array[i]=array[i-1];
        }
        array[index] = item;
        size++;
    }

    private void resize() {
        Object[] newArray = new Object[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
