package org.azelentsov.otusHw.task09InsertionSort;

import org.azelentsov.otusHw.common.BaseSort;

public class Solution09SelectionSort extends BaseSort {

    public Solution09SelectionSort(int lengthOfArray){
        populateArray(lengthOfArray);
    }

    @Override
    protected void sort(){
        shiftingBinarySearch();
        }

    private void swapping(){
        for (int currentIndex = 1; currentIndex < arrayToSort.length; currentIndex++) {
            for (int pointer = currentIndex - 1;
                 pointer >= 0 && arrayToSort[pointer] > arrayToSort[pointer + 1]; pointer--) {
//                Метод замены элементов, реализован в BaseSort
                swap(pointer + 1, pointer);
            }
        }
    }

    private void shifting(){
        int pointer;
        for (int currentIndex = 0; currentIndex < arrayToSort.length; currentIndex++){
            int currentElement = arrayToSort[currentIndex];
            for (pointer = currentIndex-1; pointer >=0 && arrayToSort[pointer] > currentElement; pointer--){
                arrayToSort[pointer+1] = arrayToSort[pointer];
            }
            arrayToSort[pointer + 1] = currentElement;
        }
    }

    private void shiftingBinarySearch(){
        int pointer;
        for (int currentIndex = 0; currentIndex < arrayToSort.length; currentIndex++){
            int currentElement = arrayToSort[currentIndex];
            int whereToPast = binarySearch(currentElement, 0, currentIndex-1);
            for (pointer = currentIndex-1; pointer >= whereToPast; pointer--){
                arrayToSort[pointer+1] = arrayToSort[pointer];
            }
            arrayToSort[pointer +1] = currentElement;
        }
    }

    private int binarySearch(int numberToCompare, int startPosition, int endPosition){
        if (endPosition<= startPosition){
            if (numberToCompare <= arrayToSort[startPosition]){
                return startPosition +1;
            } else {
                return startPosition;
            }
        }
        int mid = (startPosition + endPosition)/2;
        if (numberToCompare > arrayToSort[mid]){
            return binarySearch(numberToCompare, mid+1, endPosition);
        } else {
            return binarySearch(numberToCompare, startPosition, mid -1);
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
