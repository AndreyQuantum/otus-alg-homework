package org.azelentsov.otusHw.task20Treap;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.IOException;
import java.util.Random;

public class Solution20Treap extends BaseSort {

    private class TreapNode{
        public double y;
        public TreapNode left, right;
        public int value;

        public TreapNode(int value) {
            this.value = value;
            left = right = null;
            var yGenerator = new Random();
            y = yGenerator.nextDouble();
        }
    }

    private TreapNode tree;
    private TreapNode t1;
    private TreapNode t2;

    public Solution20Treap(int nodeNumbers) {
        populateArray(nodeNumbers);
    }

    private TreapNode insert(TreapNode t, int x){
        if (t == null) return new TreapNode(x);
        if (t.value > x){
            t.left = insert(t.left, x);
        } else if (t.value < x){
            t.right = insert(t.right, x);
        }
        return t;
    }

    private void split(TreapNode t, int x){
        if (t == null) return;
        if (t.value > x){
            insert(t1, x);
        } else if (t.value < x){
            insert(t2,x);
        }
        split(t.right, x);
        split(t.left,x);
    }

    public void insert(int x){
        if (tree == null){
            tree = new TreapNode(x);
        }
        t1 = t2 = null;
//        разделяем массив на два подмассива
        split(tree, x);
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            var testObject = new Solution20Treap(N);
            testObject.run();
        }

    }
}
