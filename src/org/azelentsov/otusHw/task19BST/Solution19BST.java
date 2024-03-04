package org.azelentsov.otusHw.task19BST;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.common.Node;

import java.io.IOException;
import java.util.Arrays;

public class Solution19BST extends BaseSort {

    public Solution19BST(int nodeNumbers) {
        populateArray(nodeNumbers);
    }



    private Node tree = null;

    public void insert(int x){
        tree = addNode(tree, x);
    }

    private Node addNode(Node node, int keyToAdd){
        if (node == null){
            return new Node(keyToAdd);
        }
        if (node.value > keyToAdd){
            node.left = addNode(node.left, keyToAdd);
        }
        if (node.value < keyToAdd){
            node.right = addNode(node.right, keyToAdd);
        }
        return node;
    }


    public boolean search(int x){
        Node currentNode = tree;
        while (currentNode != null){
            if (currentNode.value == x){
                return true;
            }
            if (x > currentNode.value){
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return false;
    }

    private Node removeNode(Node node, int valueToRemove){
//        прерываем рекурсию если ноды нету
        if (node == null) return node;
//        ищем необходимую ноду
        if (node.value > valueToRemove){
            node.left = removeNode(node.left, valueToRemove);
        }
        else if (node.value < valueToRemove){
            node.right = removeNode(node.right, valueToRemove);
        }
//        если ноду нашли - то удаляем ее
        else {
//            Если у нас левой ноды нет, то заменяем текущую ноду правой
            if (node.left == null)
                return node.right;
//            если правой ноды нет, то заменяем ее левой
            if (node.right == null)
                return node.left;
//            находим минимальный элемент в правом поддереве и заменяем значение
//            текущей ячейкой
            node.value = minValue(node.right);
//            удаляем узел приемника, значение которого было вставлено вместо узла для удаления
            node.right = removeNode(node.right, node.value);
        }
        return node;
    }

    private int minValue(Node node){
        int minValue = node.value;
        while (node.left != null){
            minValue = node.value;
            node = node.left;
        }
        return minValue;
    }

    public void remove(int x){
           removeNode(tree, x);
    }

    @Override
    protected void sort() {
        for (int element: arrayToSort){
            insert(element);
        }
    }


    public static void main(String[] args) throws RuntimeException {
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            System.out.println("\n\n\n--------Randomised array--------");
            var testObject = new Solution19BST(N);
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
            testObject = new Solution19BST(N);
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
