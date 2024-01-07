package org.azelentsov.otusHw.task03Power;

public class Solution04FibanacciBine {

    public static double calculate(int order){
        var firstHalf = Math.pow(((1+Math.sqrt(5))/2), order);
        var secondHalf = Math.pow(((1-Math.sqrt(5))/2), order);
        return (firstHalf-secondHalf)/Math.sqrt(5);
    }
    public static void main(String[] args) {
        System.out.println(Solution04FibanacciBine.calculate(20));
    }
}
