package org.azelentsov.otusHw.task01;

import org.azelentsov.otusHw.common.BaseTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Счастливые билеты 20
//
//Билет с 2N значным номером считается счастливым,
//если сумма N первых цифр равна сумме последних N цифр.
//Посчитать, сколько существует счастливых 2N-значных билетов.
//
//Начальные данные: число N от 1 до 10.
//Вывод результата: количество 2N-значных счастливых билетов.
// in:1
// out:10
// in:2
// out:670
public class solution01Senior implements BaseTask {
    @Override
    public String run(String inputCase) {
        int n = Integer.parseInt(inputCase);
        long sum = 0;
//        массив с цифрами для построчного сложения
        long[] digitsForSum = new long[n*9+1];
//        массив с результатами сложения
        long[] results = new long[n*9+1];

//        Заполняем созданный массив c цифрами единицами
        for (int count = 0; count<=9; count++){
            results[count] = 1;
        }
//      просчитываем коллличество возможных комбинаций для каждой возможной последней цифры. count=2 тк для первых двух уже рассчитано
        for (long count = 2; count<=n; count++){
//            Общее количество чисел
            long maxS = count*9;
//            Половина, после которой значения уменьшаются
            long maxT =maxS - 9;
//            Сохраняем results в digitsForSum
            for (int ind = 0; ind<=maxT; ind++){
                digitsForSum[ind] = results[ind];
            }
            //          Обнуляем результаты
            for (int ind = 0; ind<=maxS; ind++){
                results[ind] = 0;
            }
//            Выполняем главное действие - постепенно суммируем значения
//            maxT - последний индекс промежуточного числа, s- сдвиг, d - порядковый номер в комментарии
            for (int d=0; d<=9; d++){
                for (int s = 0; s<= maxT; s++){
                    results[s+d] += digitsForSum[s];
                }
            }
        }


//      Вычисляем сумму квадратов
        for (Long result : results) {
            sum += (long) Math.pow(result, 2);
        }
        return Long.toString(sum);
    }

    public static void main(String[] args) {
        solution01Senior sol = new solution01Senior();
        System.out.println(sol.run("3"));
    }
}
