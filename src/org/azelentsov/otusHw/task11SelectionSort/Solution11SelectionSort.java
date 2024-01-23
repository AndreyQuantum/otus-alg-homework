package org.azelentsov.otusHw.task11SelectionSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.task10ShellSort.Solution10ShellSort;

import java.util.Arrays;

public class Solution11SelectionSort extends BaseSort {

    public Solution11SelectionSort(int arrayLength) {
        populateArray(arrayLength);
    }

    @Override
    protected void sort() {
        for (int indexToPaste = 0; indexToPaste< arrayToSort.length; indexToPaste++){
            int minIndex = indexToPaste;
            boolean isCompared = false;
            for (int findMinElementIndex = minIndex; findMinElementIndex < arrayToSort.length; findMinElementIndex++){
                if (arrayToSort[findMinElementIndex] < arrayToSort[minIndex]){
                    minIndex=findMinElementIndex;
                    isCompared = true;
                }
            }
            if (!isCompared){
                break;
            }
            swap(indexToPaste, minIndex);
        }

    }
    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N<=1_000_000_000; N*=10){
            var bubbleTest = new Solution11SelectionSort(N);
            bubbleTest.run();
        }
    }
}
