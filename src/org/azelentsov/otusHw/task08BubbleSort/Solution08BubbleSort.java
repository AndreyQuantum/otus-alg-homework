package org.azelentsov.otusHw.task08BubbleSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.common.BaseTask;

import java.util.Arrays;

public class Solution08BubbleSort extends BaseSort {


    public Solution08BubbleSort(int lengthOfArray) {
        populateArray(lengthOfArray);
    }

    @Override
    protected void sort(){
        for (int endPointer = arrayToSort.length; endPointer>1; endPointer--){
            for (int currentIndex=1; currentIndex< endPointer; currentIndex++){
                if (arrayToSort[currentIndex-1] > arrayToSort[currentIndex]){
                    swap(currentIndex-1, currentIndex);
                }
            }
        }
    }

    private void swap(int indexA, int indexB){
        int temp = arrayToSort[indexA];
        arrayToSort[indexA] = arrayToSort[indexB];
        arrayToSort[indexB] = temp;
    }

    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N<=1_000_000; N *= 10){
            var bubbleTest = new Solution08BubbleSort(N);
            bubbleTest.run();
        }
    }

}
