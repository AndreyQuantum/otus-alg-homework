package org.azelentsov.otusHw.task22HashTable;

import org.azelentsov.otusHw.common.BaseTask;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// Реализация hashTable. Ключи - String, Значения - генерик V.
public class Solution22HashTable implements BaseTask {


    private final float loadFactor = 0.75F;

    private final int DEFAULT_SIZE = 10;


    private int hashFactor = DEFAULT_SIZE;

    private ArrayList[] values = new ArrayList[DEFAULT_SIZE];

    public Object get(String keyToGet){
        for (int i = getPosition(keyToGet); i <= values.length; i++){
            if (values[i].getFirst() == keyToGet){
                return values[i].get(1);
            }
        }
        return null;
    }
    public void put(String keyToPut, Object objectToPut){
        rehash();
        for (int i = getPosition(keyToPut); i <= hashFactor; i++){
            if (values[i] == null){
                values[i] = new ArrayList();
                values[i].add(keyToPut);
                values[i].add(objectToPut);
            }
        }
    }

    public Object delete(String keyToDelete){
        for (int i = getPosition(keyToDelete); i<= values.length; i++){
            if (values[i].getFirst() == keyToDelete){
                Object objectToDelete = values[i].get(1);
                values[i] = null;
                return objectToDelete;
            }
        }
        return null;
    }

    private int getPosition(String valueToHash){
        AtomicInteger hashCodeSum = new AtomicInteger();
        valueToHash.chars().forEach(hashCodeSum::addAndGet);
        return hashCodeSum.get() % hashFactor;
    }

    private void rehash(){
        if ( (float) values.length / (float) hashFactor > loadFactor){
            hashFactor *= 2;
            var resultValues = new ArrayList[hashFactor];
//            Проходимся по каждому элементу и переоределяем его позицию в листе
            for (ArrayList element: values){
                int newPosition = getPosition((String) element.getFirst());
                for (int i = newPosition; i <= values.length; i++){
                    if (resultValues[i] == null){
                        resultValues[i] = element;
                    }
                }
            }
            values = resultValues;
        }

    }

    @Override
    public String run(String inputCase) {
        return null;
    }
    public static void main(String[] args) {
        var objectToCheck = new Solution22HashTable();
        System.out.println(objectToCheck.run());
    }
}
