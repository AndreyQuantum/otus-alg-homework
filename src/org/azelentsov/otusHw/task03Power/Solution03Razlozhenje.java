package org.azelentsov.otusHw.task03Power;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution03Razlozhenje {
    public String run(double number, double power) {
        if (power == 0) {
            return "1.0";
        }
        if (power == 1){
            return Double.toString(number);
        }
        double result = 1;
        double numberToMultiply = 1;
        for (; power >= 1; power /= 2) {
            numberToMultiply*=number;
            if (power % 2 > 0) {
                result *= numberToMultiply;
            }

        }
        return Double.toString(result);

    }


    public static void main(String[] args) throws IOException {
        var test = new Solution03Razlozhenje();
//        System.out.println(test.run("123456789\r\n" +
//                "0\n"));
//        System.out.println(test.run(2,10));
        System.out.println(test.run(2,5));

//        System.out.println(test.run("2\r\n" +
//                "100\n"));
//        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task03Power/3.Power",new Solution03Razlozhenje());
//    }
    }
}

