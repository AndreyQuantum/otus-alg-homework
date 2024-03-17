package org.azelentsov.otusHw.task26Demukron;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.task05Arrays.src.model.PriorityQueue;
import org.azelentsov.otusHw.task25Kosaraju.Solution25Kosaraju;

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
        for (int i = 0; i < graph.length; i++){
            polustepen[i] = countNum(i);
            graphCopy[i] = graph[i];
        }
        while (graphCopy.length > 0){
            for
        }
        return null;
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
