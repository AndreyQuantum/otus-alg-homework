package org.azelentsov.otusHw.task29StrPerebor;


public class Solution29StrPerebor {
    public Solution29StrPerebor() {
    }

    public static int run(String inputPattern, String text) {
        int patternIndex = 0;
        for (int t = 0; t < text.length(); t++){
            if (text.charAt(t) != inputPattern.charAt(++patternIndex)) {
                patternIndex = 0;
            } else if (patternIndex == inputPattern.length() -1){
                return t - inputPattern.length() + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.print(Solution29StrPerebor.run("abbc", "absdbcabababbsabbcedrf"));
    }


}
