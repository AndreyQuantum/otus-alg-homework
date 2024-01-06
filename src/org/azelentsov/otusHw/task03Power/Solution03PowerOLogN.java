package org.azelentsov.otusHw.task03Power;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution03PowerOLogN implements BaseTask {
    @Override
    public String run(String inputCase) {
        double[] inputNumbers = Arrays.stream(inputCase.split("\n")).mapToDouble(Double::valueOf).toArray();
        double number = inputNumbers[0];
        double power = inputNumbers[1];

        for (double powerCounter=0; powerCounter <= power; powerCounter++){
            if (number % 2 == 0){
                number *= number;
            }
        }

        return Double.toString(number);
    }


public static void main(String[] args) throws IOException {
        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task03Power/3.Power",new Solution03PowerOLogN());
    }
}
