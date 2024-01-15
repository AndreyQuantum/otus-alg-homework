import org.azelentsov.otusHw.task05Arrays.src.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {;
        IArray singleArray = new SingleArray();
        IArray vectorArray = new VectorArray();
        IArray factorArray = new FactorArray();
        IArray matrixArray = new MatrixArray();
        IArray arrayList = new ArrayListWrapper();
        System.out.println("Add to arrays");
        testAddArray(singleArray, 10_000);
        testAddArray(vectorArray, 100_000);
        testAddArray(factorArray, 100_000);
        testAddArray(matrixArray, 100_000);
        testAddArray(arrayList, 100_000);
        System.out.println("Remove from arrays");
        testRemoveArray(singleArray);
        testRemoveArray(vectorArray);
        testRemoveArray(factorArray);
        testRemoveArray(matrixArray);
        testRemoveArray(arrayList);
        System.out.println("Add by index");
        testAddByIndexArray(singleArray);
        testAddByIndexArray(vectorArray);
        testAddByIndexArray(factorArray);
        testAddByIndexArray(matrixArray);
        testAddByIndexArray(arrayList);

    }

    private static void testAddByIndexArray(IArray data){
        long start = System.nanoTime();

        data.add(new Date(), 5);


        System.out.println(data + " testRemoveArray: " +
                (System.nanoTime() - start));

    }

    private static void testRemoveArray(IArray data){
        long start = System.nanoTime();

        data.remove(5);

        System.out.println(data + " testRemoveArray: " +
                (System.nanoTime() - start));

    }

    private static void testAddArray(IArray data, int total) {
        long start = System.nanoTime();


        for (int j = 0; j < total; j ++)
            data.add(new Date());


        System.out.println(" testAddArray: " +
                (System.nanoTime() - start));
    }
}
