package org.azelentsov.otusHw.task12HeapSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.task11SelectionSort.Solution11SelectionSort;

public class Solution12HeapSort extends BaseSort {

    public Solution12HeapSort(int arrayLength) {
        populateArray(arrayLength);
    }

    @Override
    protected void sort() {
        for (int root = arrayToSort.length/2;root >= 0; root--){
            heapify(root,arrayToSort.length);
        }
        for (int index = arrayToSort.length-1; index > 0; index--){
            swap(0, index);
            heapify(0, index);
        }
        System.out.println();
    }

    private void heapify(int root, int end){
        int max = root;
        int L = (2*root)+1;
        int R = (2*root)+2;
        if (L < end &&arrayToSort[L] > arrayToSort[max]){
            max = L;
        }
        if (R < end && arrayToSort[R] > arrayToSort[max]) {
            max = R;
        }
        if (max == root){
            return;
        }
        swap(root,max);
        heapify(max,end);
    }
    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N<=1_000_000_000; N*=10){
            var bubbleTest = new Solution12HeapSort(N);
            bubbleTest.run();
        }
    }
}
