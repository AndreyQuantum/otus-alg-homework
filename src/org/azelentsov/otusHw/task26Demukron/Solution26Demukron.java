package org.azelentsov.otusHw.task26Demukron;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution26Demukron {

    public static int[][] demucron(int[][] A) {
        int N = A.length;
//        массив для хранения входящих степеней каждой вершины
        int[] inDegree = new int[N];
//        считаем степени всех вершин
        for (int[] ints : A) {
            for (int anInt : ints) {
                inDegree[anInt]++;
            }
        }

//      ищем вершины с входящей полустепенью 0 и добавляем в очередь
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<List<Integer>> levels = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            LinkedList<Integer> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
//                берем нулевую вершину из очереди
                int vertex = queue.remove();
//                добавляем ее в текущий уровень
                level.add(vertex);
                for (int adjVertex : A[vertex]) {
//                    уменьшаем степень вершин
                    inDegree[adjVertex]--;
//                    если степень вершины равна нулю после вычитания, то добавляем в следущую очередь
                    if (inDegree[adjVertex] == 0) {
                        nextQueue.add(adjVertex);
                    }
                }
            }
            levels.add(level);
            queue = nextQueue;
        }

        // Преобразование списка уровней в требуемый формат int[][]
        int[][] levelsArray = new int[levels.size()][];
        for (int i = 0; i < levels.size(); i++) {
            levelsArray[i] = levels.get(i).stream().mapToInt(Integer::intValue).toArray();
        }

        return levelsArray;
    }

    public static void main(String[] args) {
        // Пример графа в виде вектора смежности
        int[][] A = {
                {1, 2},    // Вершина 0 смежна с 1 и 2
                {3},       // Вершина 1 смежна с 3
                {3},       // Вершина 2 смежна с 3
                {}         // Вершина 3 без смежных вершин
        };

        int[][] levels = demucron(A);
        for (int i = 0; i < levels.length; i++) {
            System.out.print("Уровень " + i + ": ");
            for (int vertex : levels[i]) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

}
