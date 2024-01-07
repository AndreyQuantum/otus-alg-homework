package org.azelentsov.otusHw.task04Fibbanaci;

public class Solution04FibanacciMatrix {

    private static long[][] matrixPower(int power){
        long[][] matrixToMultiply = {
                {1 , 1},
                {1 , 0}
        };
        long[][] result = {
                {1 , 1},
                {1 , 0}
        };
        while (power>=1){
            if (power % 2 > 0){
//                перемножение результата на число
                result = matrixMultiply(result, matrixToMultiply);
            }
            power /= 2;
    //            Возведение матрицы в квадрат
            matrixToMultiply = matrixMultiply(matrixToMultiply, matrixToMultiply);
        }

        return result;
    }
//  Функция перемножения матриц
    private static long[][] matrixMultiply(long[][] matrixA, long[][] matrixB){

        return new long[][] {
                {matrixA[0][0]*matrixB[0][0]+matrixA[0][1]*matrixB[1][0], matrixA[0][0]*matrixB[0][1]+matrixA[0][1]*matrixB[1][1]},
                {matrixA[1][0]*matrixB[0][0]+matrixA[1][1]*matrixB[0][1], matrixA[1][0]*matrixB[0][1]+matrixA[1][1]*matrixB[1][1]}
        };
    }
    public static double calculate(int order){
        return matrixPower(order-2)[0][0];

    }
    public static void main(String[] args) {
        System.out.println(calculate(17));
    }
}
