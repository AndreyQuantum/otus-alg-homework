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

    private final List<Path> tempFilesToSort = new ArrayList<>();


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

    private void mergeFileSort() throws Exception{
        /*
        Выполняем чтение двух файлов, сортировку прочитанных чисел и запись с удалением второго файла
         */
        for (int currentPosition = 0; currentPosition < tempFilesToSort.size()-1; currentPosition += 2){
            List<Integer> currentArray = new ArrayList<>();
            Path nextFile = tempFilesToSort.get(currentPosition + 1);
            Path currentFile = tempFilesToSort.get(currentPosition);
            try (var reader1 = new BufferedReader(new FileReader(currentFile.toFile()));
                 var reader2 = new BufferedReader(new FileReader(nextFile.toFile()))){
                while (reader1.ready()){
                    currentArray.add(Integer.parseInt(reader1.readLine()));
                }
                while (reader2.ready()){
                    currentArray.add(Integer.parseInt(reader2.readLine()));
                }
                mergeSort(currentArray, 0, currentArray.size()-1);
            }
            try (var writer = new BufferedWriter(new FileWriter(currentFile.toFile()))){
                for (Integer element : currentArray){
                    writer.write(element.toString());
                    writer.newLine();
                }
            }
            Files.delete(nextFile);
            tempFilesToSort.remove(currentPosition+1);
        }
    }
    private void mergeSort(List<Integer> arrayToSort, int l, int r) {
        if (l>=r) return;
        int middleIndex = (l+r)/2;
        mergeSort(arrayToSort, l, middleIndex);
        mergeSort(arrayToSort, middleIndex+1,r);
        merge(arrayToSort, l,middleIndex,r);
    }

    private void merge(List<Integer> arrayToSort, int l, int m, int r){
        int[] sortedArray = new int[r - l + 1];
        int firstHalfIndex = l;
        int secondHalfIndex = m + 1;
        int createdArrayCurrentPoint = 0;
        while (firstHalfIndex <= m && secondHalfIndex <= r){
            if (arrayToSort.get(firstHalfIndex) >= arrayToSort.get(secondHalfIndex)){
                sortedArray[createdArrayCurrentPoint++] = arrayToSort.get(secondHalfIndex++);
            } else {
                sortedArray[createdArrayCurrentPoint++] = arrayToSort.get(firstHalfIndex++);
            }
        }
        while (firstHalfIndex <= m){
            sortedArray[createdArrayCurrentPoint++] = arrayToSort.get(firstHalfIndex++);
        }
        while (secondHalfIndex <= r){
            sortedArray[createdArrayCurrentPoint++] = arrayToSort.get(secondHalfIndex++);

        }
        for (int i = l; i <= r; i++ ){
            arrayToSort.set(i, sortedArray[i - l]);
        }
    }



    public static void main(String[] args) throws IOException {

        System.out.println("Element count \t elapsed time");
        for (int N = 10; N <= 1_000_000; N *= 10) {
            var testObject = new Solution15ExternalSortES1(N, N);
            testObject.run();
        }

    }
}
