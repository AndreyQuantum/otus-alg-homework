package org.azelentsov.otusHw.task16BucketSort;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Solution16BucketSort extends BaseSort {

    private List<List<Integer>> buckets;

    private int maxNumber;


    public Solution16BucketSort(int length) {
        populateArray(length);
        maxNumber = arrayToSort[0];
        for (int element: arrayToSort){
            if (element > maxNumber){
                maxNumber = element;
            }
        }
        buckets = new ArrayList<>(length);
        for (int i = 0; i <= length; i++){
            buckets.add(new LinkedList<>());
        }
    }

    private int calculateBucketNumber(int number){
        return number * arrayToSort.length/maxNumber;
    }



    @Override
    protected void sort() {
//        Рассовываем элементы по ведрам
        for (int element: arrayToSort){
            int bucketNumber = calculateBucketNumber(element);
            var currentBucket = buckets.get(bucketNumber);
            if (currentBucket.size() == 0 ){
                currentBucket.add(element);
            } else {
                int index = 0;
                for (; index < currentBucket.size(); index++){
                    if (element < currentBucket.get(index)){
                        currentBucket.add(index, element);
                        break;
                    }
                }
                if (index == currentBucket.size()){
                    currentBucket.add(element);
                }
            }
        }
//        Собираем элементы из ведер в отсортированный массив
        int position = 0;
        for (var bucket: buckets){
            for (int element: bucket){
                arrayToSort[position++] = element;
            }
        }
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 10_000_000; N *= 10) {
            var testObject = new Solution16BucketSort(N);
            testObject.run();
        }

    }
}
