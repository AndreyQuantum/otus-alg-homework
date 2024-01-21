package org.azelentsov.otusHw.task08BubbleSort;

import org.azelentsov.otusHw.common.BaseSort;

import javax.naming.spi.StateFactory;

public class Solution08BubbleSort extends BaseSort {


    public Solution08BubbleSort(int lengthOfArray) {
        populateArray(lengthOfArray);
    }

    @Override
    protected void sort(){
        optimized();
    }

    private void standart(){
        for (int endPointer = arrayToSort.length; endPointer>1; endPointer--){
            for (int currentIndex=1; currentIndex< endPointer; currentIndex++){
                if (arrayToSort[currentIndex-1] > arrayToSort[currentIndex]){
                    swap(currentIndex-1, currentIndex);
                }
            }
        }
    }
    private void optimized(){
        for (int endPointer = arrayToSort.length; endPointer>1; endPointer--){
            boolean isNotSwapped = true;
            for (int currentIndex=1; currentIndex< endPointer; currentIndex++){
                if (arrayToSort[currentIndex-1] > arrayToSort[currentIndex]){
                    swap(currentIndex-1, currentIndex);
                    isNotSwapped = false;
                }
            }
            if (isNotSwapped){
                break;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N<=1_000_000; N*=10){
            var bubbleTest = new Solution08BubbleSort(N);
            bubbleTest.run();
        }
    }

}
