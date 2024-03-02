package org.azelentsov.otusHw.task16BucketSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.common.SortLinkedList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solution16BucketSort extends BaseSort {

    public Solution16BucketSort(int arrayLength) {
        populateArray(arrayLength);

    }

    protected void sort() {
//        находим максимальный элемент
        int max = arrayToSort[0];
        for (int a: arrayToSort){
            if (a > max){
                max = a;
            }
        }
//        увеличиваем максильный элемент на 1 чтобы потом выполнять сразу деление
        max++;
        SortLinkedList[] bucket = new SortLinkedList[arrayToSort.length];
//        помещаем числа в соответствующие ведра для сортировки
        for (int element: arrayToSort){
            int bucketNumber = (int) ((long) element * (long) arrayToSort.length/ (long) max);
//            Буду использовать свою реализацию list - SortLinkedList
            bucket[bucketNumber] = new SortLinkedList(element, bucket[bucketNumber]);
//            сортируем числа внутри ведра. Если число больше следущего - двигаем вверх
            sortBucketElements(bucket[bucketNumber]);

        }
        //          Собираем элементы из бакетов обратно в массив
        int indexToInsert = 0;
        for (SortLinkedList list : bucket){
            while (list != null && list.value != null){
                arrayToSort[indexToInsert++] = list.value;
                list = list.nextValue;
            }
        }
    }

    private static void sortBucketElements(SortLinkedList bucket) {
        SortLinkedList items = bucket;
        while (items.nextValue != null){
            if (items.value < items.nextValue.value){
                break;
            }
            int x = items.value;
            items.value = items.nextValue.value;
            items.nextValue.value = x;
            items = items.nextValue;
        }
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 10_000_000; N *= 10) {
            var testObject = new Solution16BucketSort(N);
            testObject.run();
        }

    }
}
