package org.azelentsov.otusHw.task06Ladja;

public class Solution06Ladja {

    public static long run(int bitPosition){
//        Целочисленная часть результата деления на 8 - на сколько нужно подвинуть линию горизонтали вверх
//        Дробная часть результата деления на 8 - на сколько нужно подвинуть линию вертикали влево
        return (255L<<((bitPosition / 8 ) * 8 ) | 72340172838076673L<<bitPosition % 8) ^ (1L<<bitPosition);
    }

    public static void main(String[] args) {
        System.out.println(Solution06Ladja.run(60));
    }
}
