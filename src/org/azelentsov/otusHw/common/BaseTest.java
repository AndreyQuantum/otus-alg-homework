package org.azelentsov.otusHw.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class BaseTest {

    public static void runTest(String rootPathDirectory, BaseTask taskToTest) throws IOException {
        int counter = 0;
        while (true){
            try{
                String inputData = Files.readString(Path.of(rootPathDirectory + "/test.%d.in".formatted(counter)));
                String outputData = Files.readString(Path.of(rootPathDirectory + "/test.%d.out".formatted(counter)));
                String taskResult = taskToTest.run(inputData);
                boolean testResult = taskResult.equals(outputData);
                System.out.printf("Test #%d:%b\n", counter, testResult);
                if (!testResult) {
                    System.out.printf("awaited %s, result is %s\n", outputData, taskResult);
                }
                counter++;
            } catch (NoSuchFileException e){
                break;
            }
        }
    }
}
