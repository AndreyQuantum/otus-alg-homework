package org.azelentsov.otusHw.task20Treap;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.IOException;
import java.util.Random;

public class Solution20Treap extends BaseSort {

    private TreapNode tree;
    private TreapNode t1;
    private TreapNode t2;

    private class TreapNode{
        public double priority;
        public TreapNode left, right;
        public int value;

        public TreapNode(int value) {
            this.value = value;
            priority = new Random().nextInt();
        }
    }



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


    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 100_000_000; N *= 10) {
            var testObject = new Solution20Treap(N);
            testObject.run();
        }

    }
}
