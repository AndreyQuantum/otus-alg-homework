package org.azelentsov.otusHw.common;

public class Node {
        public Node left, right;
        public int value;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
}
