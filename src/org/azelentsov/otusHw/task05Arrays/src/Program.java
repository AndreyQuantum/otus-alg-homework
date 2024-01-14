import org.azelentsov.otusHw.task05Arrays.src.model.*;

import java.util.Arrays;
import java.util.Date;

public class Program {

    public static void main(String[] args) {
//        IArray singleArray = new SingleArray();
//        IArray vectorArray = new VectorArray();
        IArray factorArray = new FactorArray();
//        IArray matrixArray = new MatrixArray();
//        testAddArray(singleArray, 10_000);
//        testAddArray(vectorArray, 100_000);
        testAddArray(factorArray, 100_000);
//        testAddArray(matrixArray, 100_000);
    }

    private static void testAddArray(IArray data, int total) {
        long start = System.currentTimeMillis();
        for (int j=0; j<= 12; j++){
            data.add(j);
        }
        data.add(1_000, 4);


//        for (int j = 0; j < total; j ++)
//            data.add(new Date());

//        data.remove(6);

        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }
}
