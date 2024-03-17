package org.azelentsov.otusHw.task27Kraskal;

import java.util.Random;

public class Solution27Kraskal {

    public static void main(String[] args) {
        int oneInt = new Random().nextInt(0,999);
        int secondInt = new Random().nextInt(0,999);
        System.out.println(oneInt);
        System.out.println(secondInt);
        long longToEdit;
        longToEdit = (long) oneInt << 32;
        longToEdit = longToEdit | secondInt;
        System.out.print(Integer.toBinaryString(oneInt) + ' ');
        System.out.println(Long.toBinaryString(secondInt));
        System.out.println(Long.toBinaryString(longToEdit));
    }
}
