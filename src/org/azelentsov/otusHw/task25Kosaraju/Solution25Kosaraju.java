package org.azelentsov.otusHw.task25Kosaraju;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.task05Arrays.src.model.PriorityQueue;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution25Kosaraju implements BaseTask {

    private final int[][] graph;

    private final int[][] invertedGraph;

    private final boolean[] visited;

    private final int[] component;
    private int comp_index = 0;

    private final PriorityQueue<Integer> queue;

    public Solution25Kosaraju(int[][] graph) {
//        Инициализируем матрицу, список посещенных вершин и очередь для DFS поиска
        this.graph = graph;
        visited = new boolean[graph.length];
//        реализовывал только priority queue, буду использовать всегда 1 приоритет
        queue = new PriorityQueue<>();
        invertedGraph = getInvertedGraph(graph);
        component = new int[graph.length];
        for (int i = 0; i < component.length; i++){
            component[i] = -1;
        }
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
//       2. Выполняем поиск в глубину и находим порядок окончания обработки вершины
        for (int vershina = 0; vershina < invertedGraph.length; vershina++){
            if (!visited[vershina]){
                dfs1(vershina);
            }
        }
//        3. Выполним поиск в глубину в исходном графе, перебирая вершины в порядке убывания

        while (queue.isElementsInQueue(1)){
            int u = queue.dequeue(1);
            if (component[u] < 0){
                dfs2(u);
                comp_index++;
            }

        }
        return Arrays.toString(component);
    }

    private void dfs2(int v){
        component[v] = comp_index;
        for (int uInd = 0; uInd < graph[v].length; uInd++){
            if (component[graph[v][uInd]] < 0){
                dfs2(graph[v][uInd]);
            }
        }
    }


    private void dfs1(int v) {
        visited[v] = true;
        for (int u = 0; u < invertedGraph[v].length; u++){
            if (invertedGraph[v][u] > 0 && !visited[u]){
                dfs1(u);
            }
        }
        queue.enqueue(1, v);
    }

    public static void main(String[] args) {
        int[][] graph = {
//                {0,1,0,0,0,0},
//                {0,0,1,0,0,0},
//                {1,0,0,0,0,0},
//                {0,0,0,0,1,0},
//                {0,0,0,0,0,1},
//                {0,0,0,0,0,0},
                {1},
                {2},
                {0},
                {4},
                {5}
        };
        var sol = new Solution25Kosaraju(graph);
        sol.run(null);
    }

}
