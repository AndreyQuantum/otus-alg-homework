package org.azelentsov.otusHw.task29BojerMur;

//  перебор символов текста и шаблона по очередно и их сравнение
public class Solution29StrBruteForce {
    public Solution29StrBruteForce() {
    }

    public static int run(String pattern, String text) {
        int pi = 0;
        for (int t = 0; t < text.length(); t++){
            if (text.charAt(t) != pattern.charAt(++pi)) {
                pi = 0;
            } else if (pi == pattern.length() - 1){
                return t - pattern.length();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        System.out.print(Solution29StrBruteForce.run("abcd", "aaaaabababcd"));
        long endTime = System.nanoTime();
        System.out.println("\nExcecution Time: " + Long.toString(endTime-startTime));
    }


}
