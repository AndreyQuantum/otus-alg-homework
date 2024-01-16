package org.azelentsov.otusHw.task07CashBitCounting;

import org.azelentsov.otusHw.task06Ladja.Solution06Ladja;

public class Solution07CashBitCounting {

    public Solution07CashBitCounting() {
        initBits();
    }

    private final int[] cashedBits = new int[256];
    private  void initBits(){
        for (int i = 0; i <256;i++){
            cashedBits[i] = countBits(i);
        }
    }

    private int countBits(long mask){
        int counter = 0;
        for (;mask >0;mask=mask>>1){
            counter += (int)(1L & mask);
        }
        return counter;
    }

    public int run(long mask){
        int count = 0;
        for ( ; mask > 0 ; mask=mask >> 8){
            count += cashedBits[(int)(mask & 255)];
        }
        return count;
    }

    public static void main(String[] args) {
        var objectToCheck = new Solution07CashBitCounting();
        System.out.println(objectToCheck.run(4611650825464578047L));
    }
}
