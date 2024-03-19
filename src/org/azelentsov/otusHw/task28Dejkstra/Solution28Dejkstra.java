package org.azelentsov.otusHw.task28Dejkstra;

import org.azelentsov.otusHw.task27Kraskal.DisjointUnion;

import java.util.ArrayList;

public class Solution28Dejkstra {

    private long[][] graph;


    private long[][] resultGraph;

    private ArrayList<long[]> sortedR;


    public Solution28Dejkstra(long[][] graph) {
        this.graph = graph;
        sortedR = new ArrayList<>();
        //        1. Создаем граф и переносим все его вершины
        resultGraph = new long[graph.length][graph.length];
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
//      1. Выбираем случайную вершину. Пусть это будет 0 вершина

    }

    public static void main(String[] args) {
//      В матрице смежности будем хранить long -последние 32 бита - номер, первые 32 бита - вес вершины
        long[][] graph = {
                {writeInt(1,8), writeInt(2,5)},
                {writeInt(3,2)},
                {writeInt(3,3)},
                {}
        };
        var test = new Solution28Dejkstra(graph);
        test.run();
    }


}
