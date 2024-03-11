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
//        Вычисляем максимальную степень ребра для построения двухмерного массива инв. графа
        int maxRebroPower = 0;
        for (int[] element: graphToInv){
            if (element.length > maxRebroPower){
                maxRebroPower = element.length;
            }
        }
//        создаем граф, который будем возвращать
        int[][] graphToReturn = new int[graphToInv.length][maxRebroPower];

//        Переворачиваем направления графа
        for (int i = 0; i < graphToInv.length; i++){
            for (int element: graphToInv[i]){
                for (int rebro: graphToReturn[element]){

                }

            }
        }
    }



    @Override
    public String run(String inputCase) {
//       1. Инвертируем связи в графе
        for (int[])
        return null;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {1},
                {2},
                {1}
        };
        var sol = new Solution25Kosaraju(graph);
    }

}
