package org.azelentsov.otusHw.task05Arrays.src.model;

public class PriorityQueue<T> {

    private final IArray<IArray<T>> array;

    public PriorityQueue(){
        array = new FactorArray<>();
    }

    public void enqueue(int priority, T data){
//        Если у нас в очереди нет необходимых приоритетов, то добавляем их
        if (priority > array.size()){
            for (int i = array.size(); i<= priority; i++){
                array.add(new FactorArray<>(), i);
            }
        }
        array.get(priority).add(data);
    }
    public T dequeue(int priority){
        if (array.size() == 0){
            return  null;
        }
        return array.get(priority).remove(0);
    }

}
