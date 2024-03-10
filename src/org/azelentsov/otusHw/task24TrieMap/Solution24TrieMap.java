package org.azelentsov.otusHw.task24TrieMap;

public class Solution24TrieMap {
    private final Node root;


    public Solution24TrieMap() {
        this.root = new Node();
    }

    private class Node{
        public Node[] children;
        public Object key;
        public Node(){
            children = new Node[128];
            key = null;
        }
    }

    public void insert(String word, Object key) {
        Node nextNode = root;
        for (Character ch : word.toCharArray()){
            if (nextNode.children[ch] == null){
                nextNode.children[ch] = new Node();
            }
            nextNode =  nextNode.children[ch];
        }
        nextNode.key = key;
    }

    public Object search(String word) {
        Node nextNode = root;
        for (int i = 0; i< word.length(); i++){
            if (nextNode == null || nextNode.children[word.charAt(i)] == null){
                return null;
            }
            if (i == word.length()-1 && nextNode.children[word.charAt(i)].key != null){
                return nextNode.children[word.charAt(i)].key;
            }
            nextNode = nextNode.children[word.charAt(i)];
        }
        return null;
    }

    public Object startsWith(String prefix) {
        Node nextNode = root;
        for (Character ch : prefix.toCharArray()){
            if (nextNode.children[ch] == null){
                return null;
            }
            nextNode = nextNode.children[ch];
        }
        return nextNode.key;
    }

    public static void main(String[] args) {
        var testObject = new Solution24TrieMap();
        testObject.insert("aaa", '1');
        testObject.insert("aaab", '2');
        System.out.println(testObject.search("aaa"));
        System.out.println(testObject.search("aaab"));
        System.out.println(testObject.search("aaac"));
        System.out.println(testObject.search("aacc"));
        System.out.println(testObject.search("aa"));
//        System.out.println(testObject.startsWith("ac"));

    }
}
