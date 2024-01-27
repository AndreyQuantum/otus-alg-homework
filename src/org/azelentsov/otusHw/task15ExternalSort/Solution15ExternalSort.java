package org.azelentsov.otusHw.task15ExternalSort;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Solution15ExternalSort extends BaseSort {

    private final String fileToSortName = "allNumbers.txt";

    private void generateTextFile(int rowCount, int maxNumber) {
        var randomInstance = new Random(12345);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToSortName))){
            for (int i = 0; i < rowCount; i++){
                Integer generatedInt = randomInstance.nextInt(1,maxNumber);
                bufferedWriter.write(generatedInt.toString());
                bufferedWriter.newLine();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var testObject = new Solution15ExternalSort();
        testObject.generateTextFile(100, 100);
    }
}
