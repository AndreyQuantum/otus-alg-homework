package org.azelentsov.otusHw.task19BST;

import org.azelentsov.otusHw.common.BaseSort;
import org.azelentsov.otusHw.task18RadixSort.Solution18RadixSort;

import java.io.IOException;

public class Solution19BST extends BaseSort {

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
    public void remove(int x){

    }

    @Override
    protected void sort() {


    }


    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
//        for (int N = 10; N <= 100_000_000; N *= 10) {
//            var testObject = new Solution19BST(N);
//            testObject.run();
//        }
        var test = new Solution19BST();
        test.insert(30);
        test.insert(5);
        test.insert(10);
        test.insert(40);
        System.out.println(test.search(10));
    }
}
