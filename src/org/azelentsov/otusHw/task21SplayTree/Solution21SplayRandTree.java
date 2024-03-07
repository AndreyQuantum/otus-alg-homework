package org.azelentsov.otusHw.task21SplayTree;

import org.azelentsov.otusHw.common.Node;
import org.azelentsov.otusHw.task19BST.Solution19BST;

import java.util.Arrays;
import java.util.Random;


class Solution21SplayRandTree extends Solution19BST {

    public Solution21SplayRandTree(int nodeNumbers) {
        super(nodeNumbers);
    }

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Метод для выполнения операции splay для узла с указанным ключом
    private Node splay(Node root, int key) {
        // Если корневой узел пуст или содержит ключ, возвращаем корень
        if (root == null || root.value == key)
            return root;

        // Если ключ меньше, чем значение корневого узла, работаем с левым поддеревом
        if (root.value > key) {
            // Если левый дочерний узел отсутствует, возвращаем корень
            if (root.left == null)
                return root;
            // Если ключ меньше значения левого дочернего узла, выполняем двойной поворот
            if (root.left.value > key) {
                // Первый шаг splay для левого левого потомка
                root.left.left = splay(root.left.left, key);
                // Правый поворот для корня
                root = rightRotate(root);
            }
            // Если ключ больше значения левого дочернего узла, выполняем zig-zag поворот
            else if (root.left.value < key) {
                // Splay для левого правого потомка
                root.left.right = splay(root.left.right, key);
                // Если левый правый потомок не пуст, выполняем левый поворот для левого потомка
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }
            // Выполняем правый поворот для корня, если левый потомок не пуст, иначе возвращаем корень
            return (root.left == null) ? root : rightRotate(root);
        }
        // Если ключ больше, чем значение корневого узла, работаем с правым поддеревом
        else {
            // Если правый дочерний узел отсутствует, возвращаем корень
            if (root.right == null)
                return root;
            // Если ключ меньше значения правого дочернего узла, выполняем zig-zag поворот
            if (root.right.value > key) {
                // Splay для правого левого потомка
                root.right.left = splay(root.right.left, key);
                // Если правый левый потомок не пуст, выполняем правый поворот для правого потомка
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            // Если ключ больше значения правого дочернего узла, выполняем двойной поворот
            else if (root.right.value < key) {
                // Splay для правого правого потомка
                root.right.right = splay(root.right.right, key);
                // Левый поворот для корня
                root = leftRotate(root);
            }
            // Выполняем левый поворот для корня, если правый потомок не пуст, иначе возвращаем корень
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    @Override
    public void insert(int x){
//        Делаем вставку со splay только в случае, если у нас отработала формула
        if (new Random().nextInt() % (arrayToSort.length +1) == 0){
            tree = insert(tree, x);
        } else {
            tree = addNode(tree, x);
        }
    }


    private Node insert(Node root, int key) {
        if (root == null)
            return new Node(key);

        root = splay(root, key);

        if (root.value == key)
            return root;

        Node node = new Node(key);
        if (root.value > key) {
            node.right = root;
            node.left = root.left;
            root.left = null;
        }
        else {
            node.left = root;
            node.right = root.right;
            root.right = null;
        }
        return node;
    }


    public static void main(String[] args) throws RuntimeException {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            System.out.println("\n\n\n--------Randomised array--------");
            var testObject = new Solution21SplayRandTree(N);
            testObject.run();
            var  startTime = System.currentTimeMillis();
            for (int i = 1; i < N; i+=10){
                if (!testObject.search(testObject.arrayToSort[i])){
                    throw new RuntimeException("Попытка искать несуществующий элемент! ");
                };
            }
            var elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println( "Search \t" + elapsedTime + "ms");
            startTime = System.currentTimeMillis();
            for (int i = 1; i < N; i+=10){
                testObject.remove(testObject.arrayToSort[i]);
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println( "Deletion \t" + elapsedTime + "ms");
            System.out.println("--------Sorted array--------");
            testObject = new Solution21SplayRandTree(N);
            Arrays.sort(testObject.arrayToSort);
            testObject.run();
            startTime = System.currentTimeMillis();
            for (int i = 1; i < N; i+=10){
                if (!testObject.search(testObject.arrayToSort[i])){
                    throw new RuntimeException("Попытка искать несуществующий элемент! ");
                };
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println( "Search \t" + elapsedTime + "ms");
            startTime = System.currentTimeMillis();
            for (int i = 1; i < N; i+=10){
                testObject.remove(testObject.arrayToSort[i]);
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println( "Deletion \t" + elapsedTime + "ms");
        }
    }
}