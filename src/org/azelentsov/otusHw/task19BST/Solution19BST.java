package org.azelentsov.otusHw.task19BST;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.IOException;

public class Solution19BST extends BaseSort {

    public Solution19BST(int nodeNumbers) {
        populateArray(nodeNumbers);
    }

    private class Node{
        Node left, right;
        int value;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
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
        if (node == null) return node;
        if (node.value > valueToRemove){
            node.left = removeNode(node.left, valueToRemove);
        }
        else if (node.value < valueToRemove){
            node.right = removeNode(node.right, valueToRemove);
        }
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
//            находим минимальный элемент в правом поддереве
            node.value = minValue(node.right);
            node.right = removeNode(node.right, node.value);
        }
        return node;
    }

    private int minValue(Node node){
        int minValue = node.value;
        while (node.left != null){
            if (node.value < minValue){
                minValue = node.value;
            }
        }
        return minValue;
    }

    public void remove(int x){
           removeNode(tree, x);
    }

    @Override
    protected void sort() {
//        for (int element: arrayToSort){
//            insert(element);
//        }
        insert(30);
        insert(40);
        insert(5);
        insert(15);
        remove(5);
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            var testObject = new Solution19BST(N);
            testObject.run();
        }
    }
}
