package org.azelentsov.otusHw.task03Power;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution03Razlozhenje {
    public double run(double number, int power) {
        double result = 1;

        while (power>=1){
            if (power % 2 >0){
                result *= number;
            }
            power /= 2;
            number *=number;
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        var test = new Solution03Razlozhenje();
//        System.out.println(test.run("123456789\r\n" +
//                "0\n"));
//        System.out.println(test.run(2,10));
        System.out.println(test.run(2,10                                                                                                                                                                                                                                                                                                                    ));

//        System.out.println(test.run("2\r\n" +
//                "100\n"));
//        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task03Power/3.Power",new Solution03Razlozhenje());
//    }
    }
}

