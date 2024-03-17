package org.azelentsov.otusHw.task26Demukron;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.task05Arrays.src.model.PriorityQueue;
import org.azelentsov.otusHw.task25Kosaraju.Solution25Kosaraju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution26Demukron implements BaseTask {


    private final int[][] graph;

    private final PriorityQueue<Integer> queue;

    private final int[] polustepen;

    private int[][] graphCopy;

    public Solution26Demukron(int[][] graph) {
//        Инициализируем матрицу, список посещенных вершин и очередь для DFS поиска
        this.graph = graph;
//        реализовывал только priority queue, буду использовать всегда 1 приоритет
        queue = new PriorityQueue<>();
        polustepen = new int[graph.length];
        graphCopy = new int[graph.length][graph.length];

    }
    @Override
    public String run(String inputCase) {
        int level = 0;
        int[] result = new int[graph.length];
        for (int i = 0; i < graph.length; i++){
            polustepen[i] = countNum(i);
            graphCopy[i] = graph[i];
        }
        while (graphCopy.length > 0){
            List<Integer> zero = new ArrayList<>();
            for (int u = 0; u < graphCopy.length; u++){
                if (polustepen[u] == 0){
                    zero.add(u);
                }
            }
            if (zero.isEmpty()) {
                System.out.println("Граф содержит циклы.");
                return null; // Граф содержит циклы, алгоритм не применим
            }
            for (int u : zero){
                result[level]+= 1;
                graphCopy[u] = null;
                recalculatePolustepen(u);
            }
            level++;
        }
        return Arrays.toString(result);
    }

    private void recalculatePolustepen(int u) {
        for (int w = 0; w < graphCopy.length; w++){
            if (graph[u][w] >=0 && polustepen[w] >0){
                polustepen[w] -= 1;
            }
        }
    }

    private int countNum(int num){
        int sum = 0;
        for (int[] row : graph){
            for (int i : row){
                if (i == num){
                    sum++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        -1 это несуществующая вершина
        int[][] graph = {
                {2,-1,-1,-1,-1,-1},
                {2, 5,-1,-1,-1,-1},
                {3,-1,-1,-1,-1,-1},
                {4,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {3,-1,-1,-1,-1, -1}
        };
        var sol = new Solution26Demukron(graph);
        sol.run(null);
    }
}
