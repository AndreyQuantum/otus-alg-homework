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
        System.out.println( arrayToSort.length + "\t" + elapsedTime + "ms");
    }
}
