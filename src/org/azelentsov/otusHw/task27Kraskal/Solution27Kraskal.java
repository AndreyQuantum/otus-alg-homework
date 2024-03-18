package org.azelentsov.otusHw.task27Kraskal;

import org.azelentsov.otusHw.task05Arrays.src.model.PriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Solution27Kraskal {

    private long[][] graph;

    private DisjointUnion dsu;

    private long[][] resultGraph;

    private ArrayList<long[]> sortedR;


    public Solution27Kraskal(long[][] graph) {
        this.graph = graph;
        dsu = new DisjointUnion(graph.length);
        sortedR = new ArrayList<>();
        //        1. Создаем граф и переносим все его вершины
        resultGraph = new long[graph.length][graph.length];
    }

    public long[][] run(){
//       2.1 добавляем все вершины графа в лист
        for (int i = 0; i < graph.length; i++) {
            for (int r = 0; r < graph[i].length; r++) {
                sortedR.add(new long[]{(long)i, graph[i][r]});
            }
        }
//       2.2 Сортируем ребра от каждой вершины по весу от меньшего к большему
        for (int i = 0; sortedR.size() > 1 && i < sortedR.size() -1; i++){

//                Делаем пока что bubble sort
//                TODO: сделать quicksort
                int currentEl =  readInt(sortedR.get(i)[1])[1];
                int nextEl =readInt(sortedR.get(i+1)[1])[1];
                if (currentEl > nextEl){
                    long[] next = sortedR.get(i+1);
                    sortedR.remove(i+1);
                    sortedR.remove(i);
                    sortedR.add(i+1, sortedR.get(i));
                    sortedR.add(i, next);

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

//        достаем ребро из отсортированного листа и удаляем его
        long[] rebroToInsert = sortedR.removeFirst();
        int rToConn = (int) rebroToInsert[0];
//      проверяем, что между нашими ребрами еще нету связи в графе
        if (!dsu.isConnected(readInt(rebroToInsert[1])[0],rToConn)){
//            Добавляем связь в граф
            for (int i = 0; i< resultGraph[rToConn].length; i++){
                if (resultGraph[rToConn][i] == 0){
                    resultGraph[rToConn][i] = rebroToInsert[1];
                    break;
                }
            }
//            добавляем связь в dsu, чтобы не соединять граф в будущем
            dsu.union(rToConn, readInt(rebroToInsert[1])[0]);
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
