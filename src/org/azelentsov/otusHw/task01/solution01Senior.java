package org.azelentsov.otusHw.task01;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class solution01Senior implements BaseTask {
    @Override
    public String run(String inputCase) {
        int n = Integer.parseInt(inputCase);
        BigInteger sum = BigInteger.ZERO;

        // Массивы для построчного сложения и хранения результатов
        BigInteger[] digitsForSum = new BigInteger[n * 9 + 1];
        BigInteger[] results = new BigInteger[n * 9 + 1];

        // Инициализация массива results единицами
        Arrays.fill(results, BigInteger.ZERO);
        for (int count = 0; count <= 9; count++) {
            results[count] = BigInteger.ONE;
        }

        // Расчет количества возможных комбинаций для каждой возможной последней цифры
        for (int count = 2; count <= n; count++) {
            int maxS = count * 9; // Крайняя граница интервала, в рамках которого будем проводить суммирование
            int maxT = maxS - 9;  // Начальная граница интервала

            // Сохраняем results в digitsForSum
            for (int ind = 0; ind <= maxT; ind++) {
                digitsForSum[ind] = results[ind];
            }

            // Обнуляем results
            Arrays.fill(results, BigInteger.ZERO);

            // Выполнение главного действия - постепенно суммируем значения
            for (int d = 0; d <= 9; d++) {
                for (int s = 0; s <= maxT; s++) {
                    results[s + d] = results[s + d].add(digitsForSum[s]);
                }
            }
        }

        // Вычисление суммы квадратов
        for (BigInteger result : results) {
            sum = sum.add(result.pow(2));
        }
        return sum.toString();
    }


public static void main(String[] args) throws IOException {
        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task01/1.Tickets",new solution01Senior());
    }
}
