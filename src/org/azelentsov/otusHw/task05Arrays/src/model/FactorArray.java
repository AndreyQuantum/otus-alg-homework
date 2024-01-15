package org.azelentsov.otusHw.task05Arrays.src.model;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private int factor;
    private int size;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        size = 0;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return size;
    }
    public int length() {
        return array.length;
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

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T objectToRemove = (T) array[index];
//        Если количество элементов в будущем массиве будет равно количеству элементов которое мы можем хранить
//        в массиве до увеличения массива, то тогда создаем массив поменьше и копируем в него данные из старого
//        массива, за исключения индекса, который надо удалить
        if (size()-1 == Math.round((100*array.length)/(factor+100)) && array.length  >=10){
            int futureCapacity = Math.round((100*array.length)/(factor+100));
            Object[] newArray = new Object[futureCapacity];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index+1, newArray, index, index-2);
            array = newArray;
        } else {
//            иначе с удаляемого индекса перетаскиваем элементы назад на 1 позицию
            for (int i = index; i< size(); i++){
                array[i] = array[i+1];
            }
        }
        size--;


        return objectToRemove;
    }

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

    private void resize() {
        Object[] newArray = new Object[array.length + array.length * factor / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
