package org.azelentsov.otusHw.task22HashTable;

import org.azelentsov.otusHw.common.BaseTask;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// Реализация hashTable. Ключи - String, Значения - генерик V.
public class Solution22HashTable implements BaseTask {

    private class HashElement{

        public String key;
        public Object value;
        public boolean isDeleted;

        public HashElement(String key, Object value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }


    }

    private final float loadFactor = 0.75F;

    private final int DEFAULT_SIZE = 10;

    private int memberCount = 0;
    private int hashFactor = DEFAULT_SIZE;

    private HashElement[] values = new HashElement[DEFAULT_SIZE];

    public Object get(String keyToGet){
        for (int i = getPosition(keyToGet); i <= values.length; i++){
            if (!values[i].isDeleted && values[i].key.equals(keyToGet)){
                return values[i].value;
            }
        }
        return null;
    }

    public void put(String keyToPut, Object objectToPut){
        rehash();
        for (int i = getPosition(keyToPut); i < values.length; i++){
            if (values[i] == null || values[i].key.equals(keyToPut)){
                values[i] = new HashElement(keyToPut, objectToPut);
                memberCount++;
                return;
            }
        }
    }

    public Object delete(String keyToDelete){
        for (int i = getPosition(keyToDelete); i< values.length; i++){
            if (values[i].key.equals(keyToDelete)){
                Object objectToDelete = values[i].value;
                values[i].isDeleted = true;
                values[i].value = null;
                values[i].key = null;
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
            HashElement[] resultValues = new HashElement[memberCount*=2];
//            Проходимся по каждому элементу и переоределяем его позицию в листе
            for (HashElement element: values){
                if (element != null && !element.isDeleted){
                    int newPosition = getPosition(element.key);
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
