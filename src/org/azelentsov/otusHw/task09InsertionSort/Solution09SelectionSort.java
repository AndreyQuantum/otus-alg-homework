package org.azelentsov.otusHw.task09InsertionSort;

import org.azelentsov.otusHw.common.BaseSort;

public class Solution09SelectionSort extends BaseSort {

    public Solution09SelectionSort(int lengthOfArray){
        populateArray(lengthOfArray);
    }

    @Override
    protected void sort(){
            swappingMethod();
        }

    private void swappingMethod(){
        for (int currentIndex = 1; currentIndex < arrayToSort.length; currentIndex++) {
            for (int pointer = currentIndex - 1;
                 pointer >= 0 && arrayToSort[pointer] > arrayToSort[pointer + 1]; pointer--) {
//                Метод замены элементов, реализован в BaseSort
                swap(pointer + 1, pointer);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N<=1_000_000; N *= 10){
            var bubbleTest = new Solution09SelectionSort(N);
            bubbleTest.run();
        }
    }
}
