package org.azelentsov.otusHw.common;

import java.util.Arrays;
import java.util.Random;

public class BaseSort{

    protected int[] arrayToSort;

    protected void sort(){}

    protected void populateArray(int length){
        arrayToSort = new int[length];
        Random random = new Random(12345);
        for (int i = 0; i<= length-1; i++){
            arrayToSort[i] = random.nextInt(0,100);
        }
    }
    protected void populateArray(int length, int maxNumber){
        arrayToSort = new int[length];
        Random random = new Random(12345);
        for (int i = 0; i<= length-1; i++){
            arrayToSort[i] = random.nextInt(0,maxNumber);
        }
    }
//    Базовые операции, которые применяются в алгоритмах сортировки
    protected void swap(int indexA, int indexB){
        int temp = arrayToSort[indexA];
        arrayToSort[indexA] = arrayToSort[indexB];
        arrayToSort[indexB] = temp;
    }



    public void run(){
        var  startTime = System.currentTimeMillis();
        sort();
        var elapsedTime = System.currentTimeMillis() - startTime;
        String lengthToDisplay = Integer.toString(arrayToSort.length);
        if (arrayToSort.length % 1000 == 0 && arrayToSort.length /1000 <= 100){
            lengthToDisplay = Integer.toString((int)(arrayToSort.length /1000)) + " T";
        }
        if (arrayToSort.length % 1_000_000 == 0 && arrayToSort.length /1_000_000 <= 100){
            lengthToDisplay = Integer.toString((int)(arrayToSort.length /1_000_000)) + " M";
        }
        System.out.println( lengthToDisplay + "\t" + elapsedTime + "ms");
    }
}
