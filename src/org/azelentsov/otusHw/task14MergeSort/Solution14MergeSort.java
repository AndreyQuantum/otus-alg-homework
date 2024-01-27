package org.azelentsov.otusHw.task14MergeSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.task13QuickSort.Solution13QuickSort;

public class Solution14MergeSort extends BaseSort {
    public Solution14MergeSort(int length) {
        populateArray(length);
        temporarySortedArray = new int[length];
    }

    private final int[] temporarySortedArray;

    @Override
    protected void sort() {
        mergeSortOptimized(0, arrayToSort.length-1);
        System.out.println();
    }

    private void mergeSort(int l, int r) {
        if (l>=r) return;
        int middleIndex = (l+r)/2;
        mergeSort(l, middleIndex);
        mergeSort(middleIndex+1,r);
        merge(l,middleIndex,r);
    }
    private void mergeSortOptimized(int l, int r) {
        if (l>=r) return;
        int middleIndex = (l+r)/2;
        mergeSort(l, middleIndex);
        mergeSort(middleIndex+1,r);
        mergeOptimized(l,middleIndex,r);
    }

    private void merge( int l, int m, int r){
        int[] sortedArray = new int[r - l + 1];
        int firstHalfIndex = l;
        int secondHalfIndex = m + 1;
        int createdArrayCurrentPoint = 0;
        while (firstHalfIndex <= m && secondHalfIndex <= r){
            if (arrayToSort[firstHalfIndex] >= arrayToSort[secondHalfIndex]){
                sortedArray[createdArrayCurrentPoint++] = arrayToSort[secondHalfIndex++];
            } else {
                sortedArray[createdArrayCurrentPoint++] = arrayToSort[firstHalfIndex++];
            }
        }
        while (firstHalfIndex <= m){
            sortedArray[createdArrayCurrentPoint++] = arrayToSort[firstHalfIndex++];
        }
        while (secondHalfIndex <= r){
            sortedArray[createdArrayCurrentPoint++] = arrayToSort[secondHalfIndex++];

        }
        for (int i = l; i <= r; i++ ){
            arrayToSort[i] = sortedArray[i-l];
        }
    }

    private void mergeOptimized( int l, int m, int r){
        int firstHalfIndex = l;
        int secondHalfIndex = m+1;
        int currentPermanentArrayIndex = l;
        while (firstHalfIndex <= m && secondHalfIndex <= r){
            if (arrayToSort[firstHalfIndex] >= arrayToSort[secondHalfIndex]){
                temporarySortedArray[currentPermanentArrayIndex++] = arrayToSort[secondHalfIndex++];
            } else {
                temporarySortedArray[currentPermanentArrayIndex++] = arrayToSort[firstHalfIndex++];
            }
        }
        while (firstHalfIndex <= m){
            temporarySortedArray[currentPermanentArrayIndex++] = arrayToSort[firstHalfIndex++];
        }
        while (secondHalfIndex <= r){
            temporarySortedArray[currentPermanentArrayIndex++] = arrayToSort[secondHalfIndex++];
        }
        if (r + 1 - l >= 0) System.arraycopy(temporarySortedArray, l, arrayToSort, l, r + 1 - l);
    }




    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 1_000_000_000; N *= 10) {
            var bubbleTest = new Solution14MergeSort(N);
            bubbleTest.run();
        }
    }
}
