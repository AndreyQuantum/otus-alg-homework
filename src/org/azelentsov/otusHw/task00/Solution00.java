package org.azelentsov.otusHw.task00;

import org.azelentsov.otusHw.common.BaseTask;
import org.azelentsov.otusHw.common.BaseTest;

import java.io.IOException;

public class Solution00 implements BaseTask {

    @Override
    public  String run(String inputCase) {
        return Integer.toString(inputCase.trim().length());
    }
    public static void main(String[] args) throws IOException {
        BaseTest.runTest("/home/andrey/IdeaProjects/otus-alg-homework/src/org/azelentsov/otusHw/task00/0.String",new Solution00());
    }
}