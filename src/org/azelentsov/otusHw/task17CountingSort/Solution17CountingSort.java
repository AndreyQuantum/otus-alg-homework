package org.azelentsov.otusHw.task17CountingSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.task16BucketSort.Solution16BucketSort;

import java.io.IOException;

public class Solution17CountingSort extends BaseSort {



    public Solution17CountingSort(int arrayLength) {
        populateArray(arrayLength);
    }

    @Override
    protected void sort() {
//        находим максимальный элемент
        int max = findMax(arrayToSort);
        max++;
//        создаем массив для подсчета колл-ва элементов, а в будущем для учета позиции вставки
        int[] count = new int[max];
//        считаем сколько раз какие числа нам встречаются в массиве
        for (int element : arrayToSort){
            count[element] += 1;
        }
//        прибавляем в текущую ячейку значение предыдущей ячейки
        for (int index = 1; index < count.length; index++){
            count[index] += count[index-1];
        }
//        создаем массив с отсортированными значениями
        int[] resultArray = new int[arrayToSort.length];
//        пополняем результирующий массив
        for (int i = arrayToSort.length -1; i >= 0; i--){
            int currentElement = arrayToSort[i];
            int position = count[currentElement];
            resultArray[position-1] = currentElement;
            count[currentElement] -= 1;
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            var testObject = new Solution17CountingSort(N);
            testObject.run();
        }

    }
}
