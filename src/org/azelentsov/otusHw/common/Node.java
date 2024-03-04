package org.azelentsov.otusHw.common;

public class Node {
        Node left, right;
        int value;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
}
