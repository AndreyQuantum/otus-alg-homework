package org.azelentsov.otusHw.task29BojerMur;

//  перебор символов текста и шаблона по очередно и их сравнение
public class Solution29StrBruteForce {
    public Solution29StrBruteForce() {
    }

    public static int run(String pattern, String text) {
        long startTime = System.nanoTime();
        int patternIndex = 0;
        for (int t = 0; t < text.length(); t++){
            if (text.charAt(t) != pattern.charAt(++patternIndex)) {
                patternIndex = 0;
            } else if (patternIndex == pattern.length() -1){
                long endTime = System.nanoTime();
                System.out.println("\nExcecution Time: " + Long.toString(endTime-startTime));
                return t - pattern.length() + 1;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("\nExcecution Time: " + Long.toString(endTime-startTime));
        return -1;
    }

    public static void main(String[] args) {

        System.out.print(Solution29StrBruteForce.run("abcd", "abcbasdbasdijabcababddabcadabcdabc"));

    }


}
