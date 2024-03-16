package org.azelentsov.otusHw.task25Kosaraju;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.task05Arrays.src.model.PriorityQueue;

public class Solution25Kosaraju implements BaseTask {

    private final int[][] graph;

    private final int[][] invertedGraph;

    private final boolean[] visited;

    private final PriorityQueue<int[]> queue;

    public Solution25Kosaraju(int[][] graph) {
//        Инициализируем матрицу, список посещенных вершин и очередь для DFS поиска
        this.graph = graph;
        visited = new boolean[graph.length];
        queue = new PriorityQueue<>();
        invertedGraph = getInvertedGraph(graph);
    }
    
    private int[][] getInvertedGraph(int[][] graphToInv){
//        создаем граф, который будем возвращать
        int[][] graphToReturn = new int[graphToInv.length][graphToInv.length];

//        Переворачиваем направления графа, те транспонируем матрицу (меняем строки со столбцами)
        for (int str = 0; str < graphToInv.length; str++){
            for (int stlb = 0; stlb < graphToInv.length; stlb++){
                graphToReturn[stlb][str] = graphToInv[str][stlb];
            }
        }
        return graphToReturn;
    }



    @Override
    public String run(String inputCase) {
//       1. Инвертируем связи в графе
        return null;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0,1,0,0,0,0},
                {0,0,1,0,0,0},
                {1,0,0,0,0,0},
                {0,0,0,0,1,0},
                {0,0,0,0,0,1},
                {0,0,0,0,0,0},
        };
        var sol = new Solution25Kosaraju(graph);
    }

}
