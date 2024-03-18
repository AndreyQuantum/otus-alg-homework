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

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX] = rank[rootX]+1;
            }
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        DisjointUnion dsu = new DisjointUnion(10); // Создаем DSU для 10 элементов

        // Соединяем некоторые элементы
        dsu.union(1, 2);
        dsu.union(2, 3);
        dsu.union(4, 5);
        dsu.union(6, 7);
        dsu.union(5, 6);

        // Проверяем, находятся ли элементы в одном множестве
        System.out.println(dsu.isConnected(1, 3)); // true
        System.out.println(dsu.isConnected(1, 4)); // false
        System.out.println(dsu.isConnected(4, 7)); // true
    }
}
