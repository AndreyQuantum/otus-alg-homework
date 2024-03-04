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

    private boolean detectRight(Node parentNode, int valueToRemove){
        return parentNode.right != null && parentNode.right.value == valueToRemove;
    }
    private boolean detectLeft(Node parentNode, int valueToRemove){
        return parentNode.left != null && parentNode.left.value == valueToRemove;
    }

    private void removeNode(Node currentNode, Node parentNode, int valueToRemove){
        if (currentNode == null){
            return;
        }
        if (currentNode.value == valueToRemove){
//            В случае, если у нас у ноды нет потомков - удаляем ее у родителя
            if (currentNode.left == null && currentNode.right == null){
                if (detectRight(parentNode, valueToRemove)){
                    parentNode.right = null;
                }
                if (detectLeft(parentNode, valueToRemove)){
                    parentNode.left = null;
                }
            }
//            в случае, если у ноды есть один потомок - удаляем ноду и вставляем вместо нее нашу ноду
            if (currentNode.left == null){
                parentNode.right = currentNode.right;
            }
            if (currentNode.right == null) {
                parentNode.left = currentNode.left;
            }
//            если у ноды есть два потомка - нужно определить, какой потомок встанет вместо удаляемой ноды
//            if (detectRight(parentNode, valueToRemove)){
//                if (currentNode.left >)
//                parentNode.right =
//            }
//            если наша нода для удаления не найдена, то идем дальше
        }
        if (currentNode.value > valueToRemove){
            removeNode(currentNode.left, currentNode, valueToRemove);
        }
        if (currentNode.value < valueToRemove){
            removeNode(currentNode.right, currentNode,valueToRemove);
        }
    }

    public void remove(int x){
           removeNode(tree, null, x);
    }

    @Override
    protected void sort() {
//        for (int element: arrayToSort){
//            insert(element);
//        }
        insert(30);
        insert(40);
        insert(5);
//        insert(15);
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
