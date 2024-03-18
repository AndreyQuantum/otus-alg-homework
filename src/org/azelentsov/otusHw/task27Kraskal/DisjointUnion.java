package org.azelentsov.otusHw.task27Kraskal;

public class DisjointUnion {
//    индекс - элемент, значение - ссылка на родительский элемент
    private int[] parent;
    // оптимизация соединения по ранку
    private int[] rank;

    public DisjointUnion(int size) {
        parent = new int[size];
        rank = new int[size];
//        каждый элемент будет ссылаться на себя, все веса будут нулевые
        for (int i = 0; i < parent.length; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }
    public int find(int x){
        if (parent[x] != x){
            parent[x] = find(parent[x]);// оптимизация сжатия пути до корня
        }
        return parent[x];
    }

//    соединение множеств с оптимизацией соединения по рангу
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

    }
}
