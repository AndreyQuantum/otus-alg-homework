package org.azelentsov.otusHw.task03Power;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Solution03Iteration implements BaseTask {
    @Override
    public String run(String inputCase) {
        String[] inputNumbers = inputCase.split("\r\n");
        BigDecimal number = new BigDecimal(inputNumbers[0]);
        double power = Double.parseDouble(inputNumbers[1]);

        BigDecimal result = new BigDecimal("1");
        for (int counter=0;counter < power; counter++){
            result  = result.multiply(number);
        }
        return Double.toString(result.setScale(11, RoundingMode.UP).doubleValue());
    }


public static void main(String[] args) throws IOException {
        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task03Power/3.Power",new Solution03Iteration());
    }
}
