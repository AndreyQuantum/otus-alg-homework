package org.azelentsov.otusHw.task05Arrays.src.model;

public class MatrixArray<T> implements IArray<T> {

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    public MatrixArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector)
            array.add(new VectorArray<T>(vector));
        array.get(size / vector).add(item);
        size ++;
    }

    @Override
    public T get(int index) {
        return array.get(index / vector).get(index % vector);
    }

    @Override
    public void add(T item, int index) {
        if (size == array.size() * vector)
            array.add(new VectorArray<T>(vector));
//        Добавляем элемент в нужную строку
        array.get(index/vector).add(item, index % vector);
//        Переносим последний элемент из предыдущей строки в 0 позицию следующего
        for (int currentRowIndex = index/vector+1; currentRowIndex < array.size(); currentRowIndex++){
            var currentRow = array.get(currentRowIndex);
            var previousRow = array.get(currentRowIndex-1);
            currentRow.add(previousRow.get(previousRow.size()-1),0);
            previousRow.remove(previousRow.size());
        }
        size++;
    }


    @Override
    public T remove(int index) {
//        Удаляем элемент
        T elementToRemove = array.get(index/vector).remove(index%vector);
//        Переносим нулевой элемент следующей строки вместо последнего элемента предыдущей
        for (int currentRowIndex = index/vector+1; currentRowIndex < array.size(); currentRowIndex++){
            var currentRow = array.get(currentRowIndex);
            var previousRow = array.get(currentRowIndex-1);
            previousRow.add(currentRow.get(0), previousRow.size());
            currentRow.remove(0);
        }
        size--;
        return elementToRemove;
    }
}
