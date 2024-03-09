package org.azelentsov.otusHw.task22HashTable;

import org.azelentsov.otusHw.common.BaseTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// Реализация hashTable. Ключи - String, Значения - генерик V.
public class Solution22HashTable implements BaseTask {


    private final float loadFactor = 0.75F;

    private final int DEFAULT_SIZE = 10;

    private int memberCount = 0;
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
        for (int i = getPosition(keyToPut); i < values.length; i++){
            if (values[i] == null || values[i].getFirst() == keyToPut){
                values[i] = new ArrayList();
                values[i].add(keyToPut);
                values[i].add(objectToPut);
                memberCount++;
                return;
            }
        }
    }

    public Object delete(String keyToDelete){
        for (int i = getPosition(keyToDelete); i< values.length; i++){
            if (values[i].getFirst() == keyToDelete){
                Object objectToDelete = values[i].get(1);
                values[i] = null;
                memberCount--;
                return objectToDelete;
            }
        }
        return null;
    }

    private int getPosition(String valueToHash){
        AtomicInteger hashCodeSum = new AtomicInteger();
        valueToHash.chars().forEach(hashCodeSum::addAndGet);
        return  hashCodeSum.get() % hashFactor;
    }

    private void rehash(){
        if ( (float) memberCount / (float) hashFactor > loadFactor){
            hashFactor = memberCount;
            var resultValues = new ArrayList[memberCount*=2];
//            Проходимся по каждому элементу и переоределяем его позицию в листе
            for (ArrayList element: values){
                if (element != null){
                    int newPosition = getPosition((String) element.getFirst());
                    for (int i = newPosition; i < values.length; i++){
                        if (resultValues[i] == null){
                            resultValues[i] = element;
                            break;
                        }
                    }
                }
            }
            values = resultValues;
        }

    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

    @Override
    public String run(String inputCase) {
        return null;
    }
    public static void main(String[] args) {
        var objectToCheck = new Solution22HashTable();
        for (int i = 0; i <= 100; i++){
            objectToCheck.put(generateRandomString(10),  i );
        }
        System.out.println(objectToCheck.get("test101"));
        System.out.println(objectToCheck.delete("test101"));
        System.out.println(objectToCheck.get("test1"));
        System.out.println(objectToCheck.delete("test1"));

    }
}
