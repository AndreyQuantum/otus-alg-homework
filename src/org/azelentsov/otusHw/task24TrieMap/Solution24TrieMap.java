package org.azelentsov.otusHw.task24TrieMap;

import org.azelentsov.otusHw.common.BaseSort;

import static org.azelentsov.otusHw.task22HashTable.Solution22HashTable.generateRandomString;

public class Solution24TrieMap extends BaseSort {
    private final Node root;
    private final String[] prefixesToManipulate;

    public Solution24TrieMap(int elementCount) {
        this.root = new Node();
        populateArray(elementCount);
        prefixesToManipulate = new String[elementCount];
    }

    @Override
    protected void sort() {
        for (String pr : prefixesToManipulate){
            if (search(pr) == null){
                throw new RuntimeException("Попытка искать несуществующий элемент!");
            };
        }
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
        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 1_000_000_000; N *= 10) {
            var testObject = new Solution24TrieMap(N);
            for (int i = 0; i< testObject.arrayToSort.length;i++){
                var stringToInsert = generateRandomString(testObject.arrayToSort[i]);
                testObject.insert(stringToInsert, testObject.arrayToSort[i]);
                testObject.prefixesToManipulate[i] = stringToInsert;
            }
            testObject.run();
        }
//        System.out.println(testObject.startsWith("ac"));

    }
}
