package org.azelentsov.otusHw.task06Ladja;

public class Solution06Ladja {

    public static long run(long mask){
//        Высчитываем позицию ладьи ( порядковый номер бита, занятый ладьей в матрице)
        int bitPosition = bitPosition(mask);
//        Целочисленная часть результата деления на 8 - на сколько нужно подвинуть линию горизонтали вверх
//        Дробная часть результата деления на 8 - на сколько нужно подвинуть линию вертикали влево
        return (255L<<((bitPosition / 8 ) * 8 ) | 72340172838076673L<<bitPosition % 8) ^ mask;
    }

    public static int bitPosition(long mask){
        int count = 0;
        for (;mask > 1; mask = mask >>1){
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Solution06Ladja.run(524288));
    }
}
