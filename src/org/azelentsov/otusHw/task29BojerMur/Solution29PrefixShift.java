package org.azelentsov.otusHw.task29BojerMur;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

// если у нас не совпадает первая и последняя буква шаблона с текстом, то перепрыгиваем
public class Solution29PrefixShift {

    private String pattern;
    private int[] allChars;
    public Solution29PrefixShift(String pattern) {
        this.pattern = pattern;
        allChars = new int[128];
        //        заполняем таблицу сдвига
        Arrays.fill(allChars, pattern.length());
//        для букв из паттерна заполняем таблицу сдвига в обратном порядке
        for (int i = 0; i < pattern.length() -1; i++){
            allChars[pattern.charAt(i)] = i + 1;
        }
    }

    public int run(String text){

        int i = 0;
        for (int t = 0; t < text.length(); t++){
//            Если символ нашего текста не совпадает с символом шаблона
            if (i == pattern.length() -1){
                return t - pattern.length() + 1;
            }
            if (text.charAt(t) != pattern.charAt(++i)){
//                то сдвигаем индекс на колл-во символов из таблицы сдвига
                t += allChars[pattern.charAt(i)];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        var pattern = new Solution29PrefixShift("abcd");
        long startTime = System.currentTimeMillis();
        System.out.println(pattern.run("abcbasdbasdijabcababddabcadabcdabc"));
        long endTime = System.currentTimeMillis();
        System.out.println("Excecution Time: " + Long.toString(endTime-startTime));

    }
}
