package org.azelentsov.otusHw.task27Kraskal;

import java.util.Arrays;
import java.util.Random;

public class Solution27Kraskal {

    private long[][] graph;

    public Solution27Kraskal(long[][] graph) {
        this.graph = graph;

    }

    public static long writeInt(int a, int b){
        return ((long) a  << 32) | b ;
    }

    public static int[] readInt(long l){
        int a = (int) (l >> 32);
        return new int[]{a,(int) (l)};
    }

    public static void main(String[] args) {
//      В матрице смежности будем хранить long - первые 32 бита - вес вершины, последние - номер
        long[][] graph = {
                {writeInt(1,1), writeInt(2,2)},
                {writeInt(3,2)},
                {writeInt(3,3)},
                {}
        };

    }
}
