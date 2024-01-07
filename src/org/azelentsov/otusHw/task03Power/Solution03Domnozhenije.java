package org.azelentsov.otusHw.task03Power;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution03Domnozhenije implements BaseTask {
    @Override
    public String run(String inputCase) {
        String[] inputNumbers = inputCase.split("\r\n");
        BigDecimal number = new BigDecimal(inputNumbers[0]);
        double power = Double.parseDouble(inputNumbers[1]);
        if (power == 0){
            return "1.0";
        }
        BigDecimal result = new BigDecimal(number.toString());
        double currentPower = 1;
//        Возводим наше число в квадрат, пока степень не перестанет быть кратной двум
        for (;currentPower*2 < power;currentPower*=2){
            result = result.multiply(result);
        }

//        Возводим в степень обычным методом (перемножаем результат на число
        for (;currentPower < power;currentPower++){
            result = result.multiply(number);
        }
        if (result.scale() > 1){
            result=result.setScale(11, RoundingMode.UP);
        } else{
            result = result.setScale(1, RoundingMode.UP);
        }
        return result.toString();

    }



public static void main(String[] args) throws IOException {
        var test = new Solution03Domnozhenije();
//    System.out.println(test.run("123456789\r\n" +
//                "0\n"));
        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task03Power/3.Power",new Solution03Domnozhenije());
    }
}
