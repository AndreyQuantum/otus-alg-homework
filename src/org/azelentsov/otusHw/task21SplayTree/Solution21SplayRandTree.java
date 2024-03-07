package org.azelentsov.otusHw.task21SplayTree;

import org.azelentsov.otusHw.common.Node;
import org.azelentsov.otusHw.task19BST.Solution19BST;

import java.util.Arrays;

//  Наследуемся от обычного бинарного дерева, так как алгоритм изменяет фактически только функцию вставки
public class Solution21SplayRandTree extends Solution19BST {


    public Solution21SplayRandTree(int nodeNumbers) {
        super(nodeNumbers);
    }

    // Функция splay
    private Node splay(Node root, int key) {
        if (root == null || root.value == key)
            return root;

        if (root.value > key) {
            if (root.left == null)
                return root;
            if (root.left.value > key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            }
            else if (root.left.value < key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }
            return (root.left == null) ? root : rightRotate(root);
        }
        else {
            if (root.right == null)
                return root;
            if (root.right.value > key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            else if (root.right.value < key) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    @Override
    public void insert(int x) {
        tree = insert(tree, x);
    }

    // Вставка ключа
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



    private Node rightRotate(Node node){
        var leftChild = node.left;
        node.right = leftChild.right;
        leftChild.right = node;
        return leftChild;
    }

    private Node leftRotate(Node node){
        var rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        return  rightChild;
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
