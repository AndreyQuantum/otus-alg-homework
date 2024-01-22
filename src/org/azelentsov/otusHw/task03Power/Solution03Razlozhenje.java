package org.azelentsov.otusHw.task03Power;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution03Razlozhenje {
    public static double run(double x, int n) {
        double result=1;
        if (n<0){
            x=1/x;
        }
        for (; n != 0; n/=2){
            if (n % 2==1||n % 2==-1){
                result*=x;
            }
            x*=x;
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
//        System.out.println(test.run("123456789\r\n" +
//                "0\n"));
//        System.out.println(test.run(2,10));
        System.out.println(Solution03Razlozhenje.run(0.9,3653333));
//        System.out.println(test.run("2\r\n" +
//                "100\n"));
//        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task03Power/3.Power",new Solution03Razlozhenje());
//    }
    }
}

