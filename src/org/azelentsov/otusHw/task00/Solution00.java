package org.azelentsov.otusHw.task00;

import org.azelentsov.otusHw.common.BaseTask;

public class Solution00 implements BaseTask {

    @Override
    public  String run(String inputCase) {
        return Integer.toString(inputCase.trim().length());
    }
}