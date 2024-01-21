package org.azelentsov.otusHw.task10ShellSort;

import org.azelentsov.otusHw.common.BaseSort;

public class Solution10ShellSort extends BaseSort {

    public Solution10ShellSort(int lengthOfArray){
        populateArray(lengthOfArray);
    }

    private int gapCalculate(int currentGap){
        return calculateKnuthGap(currentGap);
    }

    private int calculateFrankLazarusGap(int currentGap){
        return arrayToSort.length/(2^currentGap)-1;
    }

    private int calculateKnuthGap(int currentGap){
        return (3^currentGap-1)/2;
    }

    @Override
    protected void sort(){
        for (int gap = gapCalculate(arrayToSort.length); gap>0; gap = gapCalculate(gap)){
            for (int j = gap; j < arrayToSort.length; j++){
                for (int i = j; i>=gap && arrayToSort[i-gap] > arrayToSort[i]; i-=gap){
                    swap(i-gap, i);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N<=1_000_000_000; N*=10){
            var bubbleTest = new Solution10ShellSort(N);
            bubbleTest.run();
        }
    }
}

