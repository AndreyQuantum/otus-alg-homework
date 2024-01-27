package org.azelentsov.otusHw.task13QuickSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.task12HeapSort.Solution12HeapSort;

import java.util.Arrays;

public class solution13QuickSort extends BaseSort {
    private final int N;

    public solution13QuickSort(int arrayLength) {
        populateArray(arrayLength);
        N = arrayLength;
    }

    @Override
    protected void sort() {
        System.out.println();
        quickSort(0, N - 1);
        System.out.println();
    }

    private void quickSort(int l, int r) {
//        условие выхода из рекурсии - чтобы левая граница не зашла на правую
        if (l >= r) return;
//        выполняем основную суть алгоритма - переносим левую часть влево, правую вправо от pivot
        int middleElementIndex = split(l, r);
//        отсортировываем так же левую и правую часть
        quickSort(l, middleElementIndex - 1);
        quickSort(middleElementIndex, r);
    }

    private int split(int l, int r) {
/*     Идея - делим алгоритм на 3 части:
        1)  Часть, меньшая опорного эл-та
        2) Часть больше опорного эл-та
        3) Неотсортированная часть массива
        Увеличиваем постоянно неотсортированную часть, если первый эл-т неотсортированной
        части меньше опорного эл-та, тогда свапаем последний элемент меньшей части и увеличиваем
        границу меньшей части ан 1
        */
        int pivotIndex = r;
        int lastLowerPartIndex = l-1;
        for (int lastUnsortedIndex = l; lastUnsortedIndex <= r; lastUnsortedIndex++){
            if (arrayToSort[lastUnsortedIndex] <= arrayToSort[pivotIndex]){
                swap(++lastLowerPartIndex, lastUnsortedIndex);
            }
        }
        return lastLowerPartIndex;
    }

    public static void main(String[] args) {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 1_000_000_000; N *= 10) {
            var bubbleTest = new solution13QuickSort(N);
            bubbleTest.run();
        }
    }
}
