package org.azelentsov.otusHw.task15ExternalSort;

import org.azelentsov.otusHw.common.BaseSort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution15ExternalSortES1 extends BaseSort {
    public Solution15ExternalSortES1(int N, int T) throws IOException {
        generateTextFile(N, T);
        populateArray(N);
    }

    private final Path fileToSortName = Paths.get("allNumbers.txt");

    private List<Path> tempFilesToSort = new ArrayList<>();


    private void generateTextFile(int rowCount, int maxNumber) {
        var randomInstance = new Random(12345);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToSortName.toFile()))){
            for (int i = 0; i < rowCount; i++){
                int generatedInt = randomInstance.nextInt(1,maxNumber);
                bufferedWriter.write(Integer.toString(generatedInt));
                bufferedWriter.newLine();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void splitFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileToSortName.toFile()))) {
            for (int position = 0;reader.ready(); position++){
                Path pathToCurrentFile = Files.createTempFile(Integer.toString(position),"_merge");
                try (var localFileWriter = new BufferedWriter(new FileWriter(pathToCurrentFile.toFile()))){
                    localFileWriter.write(reader.readLine());
                    tempFilesToSort.add(pathToCurrentFile);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void sort() {
        try{
            //            Делим большой файл на много маленьких. Название файла - позиция, содержимое - последовательность
            splitFile();
            while (tempFilesToSort.size() > 1){
//                Читаем и сортируем файлы пока не будет одного большого файла
                mergeFileSort();
            }
//            Перемещаем этот файл рядом с исходным
            Files.move(tempFilesToSort.getFirst(), Paths.get("allNumbers_sorted.txt"), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void fileUnionToFirstFile(Path fileToWrite1, Path fileToWrite2, Path fileToRead){
        try (var writer1 = new BufferedWriter(new FileWriter(fileToWrite1.toFile(),true));
            var writer2 = new BufferedWriter(new FileWriter(fileToWrite2.toFile(), true));
             var reader1 = new BufferedReader(new FileReader(fileToRead.toFile()))){
            while (reader1.ready()){
                writer1.write(reader1.readLine());
                writer1.newLine();
                writer2.write(reader1.readLine());
                writer2.newLine();
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void mergeFileSort() throws Exception{
        /*
        Выполняем чтение двух файлов, сортировку прочитанных чисел и запись с удалением второго файла
         */
        List<Path> nextTempPathList = new ArrayList<>();
        int currentPosition = 0;
        for (; currentPosition < tempFilesToSort.size()-1; currentPosition += 2){
            Path currentFile = tempFilesToSort.get(currentPosition);
            Path nextFile = tempFilesToSort.get(currentPosition + 1);
            if (currentPosition + 3 == tempFilesToSort.size()){
                System.out.println("сработало");
                fileUnionToFirstFile(currentFile, nextFile, tempFilesToSort.get(currentPosition+2));
            }
            Path resultTempFile = Files.createTempFile(Integer.toString(nextTempPathList.size()-1),"_");
            sortElementBetweenFiles(currentFile, nextFile, resultTempFile);
            nextTempPathList.add(resultTempFile);
        }
        System.out.println("-----------------");
        System.out.println(tempFilesToSort);
        for (int i = 0; i < tempFilesToSort.size(); i++){
            var reader = new BufferedReader(new FileReader(tempFilesToSort.get(i).toFile()));
            while (reader.ready()){
                System.out.println(reader.readLine());
            }
            reader.close();
            System.out.println("-");
        }
        System.out.println(currentPosition);
        System.out.println("tempPath");
        System.out.println(nextTempPathList);
        for (int i = 0; i < nextTempPathList.size(); i++){
            var reader = new BufferedReader(new FileReader(nextTempPathList.get(i).toFile()));
            while (reader.ready()){
                System.out.println(reader.readLine());
            }
            reader.close();
            System.out.println("-");
        }
        tempFilesToSort = nextTempPathList;
    }

    private void sortElementBetweenFiles(Path currentFile, Path nextFile, Path resultTempFile) throws IOException {
        try (var reader1 = new BufferedReader(new FileReader(currentFile.toFile()));
             var reader2 = new BufferedReader(new FileReader(nextFile.toFile()));
             var resultWriter = new BufferedWriter(new FileWriter(resultTempFile.toFile(), false))){
            while (reader1.ready() || reader2.ready()){
                if (!reader1.ready()){
                    resultWriter.write(reader2.readLine());
                    resultWriter.newLine();
                    continue;
                }
                if (!reader2.ready()){
                    resultWriter.write(reader1.readLine());
                    resultWriter.newLine();
                    continue;
                }
                int[] tempResultArray = new int[]{Integer.parseInt(reader1.readLine()), Integer.parseInt(reader2.readLine())};
                if (tempResultArray[0] <= tempResultArray[1]){
                    for (int result : tempResultArray){
                        resultWriter.write(Integer.toString(result));
                        resultWriter.newLine();
                    }
                } else {
                    for (int i = tempResultArray.length-1; i >=0; i--){
                        resultWriter.write(Integer.toString(tempResultArray[i]));
                        resultWriter.newLine();
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 10; N *= 10) {
            var testObject = new Solution15ExternalSortES1(N, N);
            testObject.run();
        }

    }
}
