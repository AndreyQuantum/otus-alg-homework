package org.azelentsov.otusHw.task29BojerMur;

import java.util.Arrays;

// если у нас не совпадает первая и последняя буква шаблона с текстом, то перепрыгиваем
public class Solution29PrefixShift {

    private String pattern;
    private int[] allChars;
    public Solution29PrefixShift(String pattern) {
        this.pattern = pattern;
        allChars = new int[128];
        //        заполняем таблицу сдвига
        Arrays.fill(allChars, pattern.length());
//        для букв из паттерна заполняем таблицу сдвига в обратном порядке кроме последнего элемента
        for (int i = 0; i < pattern.length() - 1; i++){
            allChars[pattern.charAt(i)] = 1;
        }
    }

    public int run(String text){
        int m = 0;
       for (int i = 0; i <= text.length(); i+=allChars[text.charAt(i)]){
           if (text.charAt(i) == pattern.charAt(m)){
               m++;
           } else {
               m = 0;
           }
            if (m == pattern.length() -1){
                return  i;
            }
       }
       return -1;
    }

    public static void main(String[] args) {
        var pattern = new Solution29SuffixShift("abcda");
        long startTime = System.nanoTime();
        System.out.println(pattern.run("zdrevababasdavaboekdajabcdaoaksdqw"));
        long endTime = System.nanoTime();
        System.out.println("Excecution Time: " + Long.toString(endTime-startTime));

    }
}
