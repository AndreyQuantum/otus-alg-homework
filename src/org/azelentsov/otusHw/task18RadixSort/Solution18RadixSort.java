package org.azelentsov.otusHw.task18RadixSort;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.IOException;

public class Solution18RadixSort extends BaseSort {


    private double razryadMnozhytel = 1;
    public Solution18RadixSort(int arrayLength) {
        populateArray(arrayLength, 999);
    }

    private int max;
    @Override
    protected void sort() {
//        находим максимальный элемент
        max = findMax(arrayToSort);
        max++;
//        Выясняем сколько разрядов у максимального числа
        int razrCount= 1;
        for (double i = 0.1; max * i >1; i *= 0.1){
            razrCount++;
        }

//        поразрядно сортируем все числа
        for (int i = 1; razrCount >= i; i++){
            sortRazyad();
            razryadMnozhytel *= 0.1;
        }
    }

    private void sortRazyad(){
        //        создаем массив для подсчета колл-ва элементов, а в будущем для учета позиции вставки
        int[] count = new int[10];
//        считаем сколько раз какие числа нам встречаются в массиве
        for (int element : arrayToSort){
            count[numberInRazryad(element)] += 1;
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
            int positionInCount = numberInRazryad(currentElement);
            int position = count[positionInCount];
            resultArray[position-1] = currentElement;
            count[positionInCount] -= 1;
        }
        arrayToSort = resultArray;
    }


    private int numberInRazryad(int number){
//        функция для нахождения разряда числа по его номеру.
        number *= razryadMnozhytel;
        return number % 10;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            var testObject = new Solution18RadixSort(N);
            testObject.run();
        }

    }
}
