package org.azelentsov.otusHw.task23Trie;

public class Solution22Trie {
    private final Node root;


    public Solution22Trie() {
        this.root = new Node();
    }

    private class Node{
        public Node[] children;
        public boolean isLast;
        public Node(){
            children = new Node[128];
            isLast = false;
        }
    }

    public void insert(String word) {
        Node nextNode = root;
        for (Character ch : word.toCharArray()){
            if (nextNode.children[ch] == null){
                nextNode.children[ch] = new Node();
            }
            nextNode =  nextNode.children[ch];
        }
        nextNode.isLast = true;
    }

    public boolean search(String word) {
        Node nextNode = root;
        for (int i = 0; i< word.length(); i++){
            if (nextNode == null || nextNode.children[word.charAt(i)] == null){
                return false;
            }
            if (i == word.length()-1 && nextNode.children[word.charAt(i)].isLast){
                return true;
            }
            nextNode = nextNode.children[word.charAt(i)];
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        Node nextNode = root;
        for (Character ch : prefix.toCharArray()){
            if (nextNode.children[ch] == null){
                return false;
            }
            nextNode = nextNode.children[ch];
        }
        return true;
    }

    public static void main(String[] args) {
        var testObject = new Solution22Trie();
        testObject.insert("aaa");
        testObject.insert("aaab");
//        System.out.println(testObject.search("aaa"));
//        System.out.println(testObject.search("aaab"));
//        System.out.println(testObject.search("aaac"));
//        System.out.println(testObject.search("aacc"));
//        System.out.println(testObject.search("aa"));
        System.out.println(testObject.startsWith("ac"));

    }
}
