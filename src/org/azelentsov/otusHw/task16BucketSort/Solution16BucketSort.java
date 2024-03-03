package org.azelentsov.otusHw.task16BucketSort;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.common.SortLinkedList;

import java.io.IOException;

public class Solution16BucketSort extends BaseSort {

    public Solution16BucketSort(int arrayLength) {
        populateArray(arrayLength, 999);

    }

    protected void sort() {
//        находим максимальный элемент
        int max = findMax(arrayToSort);

//        увеличиваем максильный элемент на 1 чтобы потом выполнять сразу деление
        max++;
        SortLinkedList[] bucket = new SortLinkedList[arrayToSort.length];
//        помещаем числа в соответствующие ведра для сортировки

        for (int element: arrayToSort){
            int bucketNumber = (int) (((long) element * (long) arrayToSort.length)/ (long) max);
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
                list = list.next;
            }
        }
    }

    private static void sortBucketElements(SortLinkedList bucket) {
        SortLinkedList items = bucket;
        while (items.next != null){
            if (items.value < items.next.value){
                break;
            }
            int x = items.value;
            items.value = items.next.value;
            items.next.value = x;
            items = items.next;
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
