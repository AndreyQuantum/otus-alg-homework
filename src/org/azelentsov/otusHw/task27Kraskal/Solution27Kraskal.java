package org.azelentsov.otusHw.task27Kraskal;

import java.util.Arrays;
import java.util.Random;

public class Solution27Kraskal {

    private long[][] graph;

    private DisjointUnion dsu;

    private long[][] resultGraph;


    public Solution27Kraskal(long[][] graph) {
        this.graph = graph;
        dsu = new DisjointUnion(graph.length);
        //        1. Создаем граф и переносим все его вершины
        resultGraph = new long[graph.length][graph.length];
    }

    public long[][] run(){

//       2. Сортируем ребра от каждой вершины по весу от меньшего к большему
        for (int i = 0; i < graph.length; i++){
            for (int r = 0; r < graph[i].length-1; r++){
//                Делаем пока что bubble sort
//                TODO: сделать quicksort
                int currentEl =  readInt(graph[i][r])[1];
                int nextEl = readInt(graph[i][r+1])[1];
                if (currentEl > nextEl){
                    long next = graph[i][r+1];
                    graph[i][r+1] = graph[i][r];
                    graph[i][r] = next;
                }
            }
        }
        int rCount = 0;
//        Добавляем ребра в новый граф, пока условие не выполнится
        while (rCount <= resultGraph.length -1){
            addMinR();
            rCount++;
        }
        return resultGraph;
    }

    private void addMinR() {
//        Находим ребро, у которой первый элемент в связи будет минимальным
        int min = 0;
        int minR = 0;
        for (int i = 0; i< graph.length; i++){
            if (graph[i].length >0 && readInt(graph[i][0])[1] < min){
                min = readInt(graph[i][0])[1];
                minR = i;
            }
        }
//        достаем ребро из исходного графа и удаляем его
        long rebroToInsert = graph[minR][0];
//      проверяем, что между нашими ребрами еще нету связи в графе
        if (!dsu.isConnected(readInt(rebroToInsert)[0],minR)){
//            Добавляем связь в граф
            for (int i = 0; i< resultGraph[minR].length; i++){
                if (resultGraph[minR][i] == 0){
                    resultGraph[minR][i] = rebroToInsert;
                    break;
                }
            }
//            добавляем связь в dsu, чтобы не соединять граф в будущем
            dsu.union(minR, readInt(rebroToInsert)[0]);
//            удаляем ребро из графа
        }
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

    public static void main(String[] args) {
//      В матрице смежности будем хранить long -последние 32 бита - номер, первые 32 бита - вес вершины
        long[][] graph = {
                {writeInt(1,8), writeInt(2,5)},
                {writeInt(3,2)},
                {writeInt(3,3)},
                {}
        };
        var test = new Solution27Kraskal(graph);
        test.run();
    }
}
