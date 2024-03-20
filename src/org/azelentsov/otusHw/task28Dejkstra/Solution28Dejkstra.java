package org.azelentsov.otusHw.task28Dejkstra;


import java.util.ArrayList;

public class Solution28Dejkstra {

    private long[][] graph;


    private long[][] resultGraph;

    private Node[] vertexes;
    private ArrayList<long[]> sortedR;

    private class Node{
        public int cost = Integer.MAX_VALUE;
        public int previousHop = -1;
        private boolean known = false;
    }


    public Solution28Dejkstra(long[][] graph) {
        this.graph = graph;
        sortedR = new ArrayList<>();
        //        1. Создаем граф и переносим все его вершины
        resultGraph = new long[graph.length][graph.length];
        vertexes = new Node[graph.length];
    }

    //    функции для упаковки 2 int в один long
    public static long writeInt(int a, int b){
        return ((long) a  << 32) | b ;
    }
//    функции для чтения 2 int из одного long
    public static int[] readInt(long l){
        int a = (int) (l >> 32);
        return new int[]{a,(int) (l)};
    }

    private void run() {
//        0. Подготавливаем объекты с вершинами. Индексом ноды будет являться номер вершины.
//        в качестве начальной вершины будет 0
        for (int i = 1; i < vertexes.length; i++){
            vertexes[i] = new Node();
        }
        vertexes[0] = new Node();
        vertexes[0].cost = 0;
        int lv = 0;
        for (int count = 0; count < vertexes.length; count++){
//            Смотрим на соседей в матрице смежности
            for (long ver : graph[lv]){
                int[] vData = readInt(ver);
                int w = vData[1];
                int v = vData[0];
//                если цена до вершины+цена до текущей вершины меньше чем цена записанной в вершине
                int calcCost = vertexes[lv].cost + w;
                if (calcCost < vertexes[v].cost){
//                    тогда перезаписываем кост до вершины и меняем путь до это вершины
                    vertexes[v].cost = calcCost;
                    vertexes[v].previousHop = lv;
                }

            }
//            достаем вершину с наименьшей стоимостью пути
            lv = getLowestCostNode();
//            помечаем вершину как известную
            vertexes[lv].known = true;
        }

    }
    private int getLowestCostNode(){
        int lowerCostNodeIndex = 1;
        for (int i = 1; i < vertexes.length; i++){
            if (!vertexes[i].known
                    && vertexes[lowerCostNodeIndex].cost > vertexes[i].cost){
                lowerCostNodeIndex = i;
            }
        }
        return lowerCostNodeIndex;
    }

    public static void main(String[] args) {
//      В матрице смежности будем хранить long -последние 32 бита - номер, первые 32 бита - вес вершины
        long[][] graph = {
                {writeInt(3,5)},
                {writeInt(3,3), writeInt(5,8)},
                {writeInt(4,4)},
                {writeInt(0,5), writeInt(1,3), writeInt(5,1)},
                {writeInt(2,4)},
                {writeInt(1,8), writeInt(3,1), writeInt(6,6), writeInt(7,6)},
                {writeInt(5,6), writeInt(7,7)},
                {writeInt(5,6), writeInt(6,7)},
        };
        var test = new Solution28Dejkstra(graph);
        test.run();
    }


}
