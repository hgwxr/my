package com.hgwxr.algorithm;

import java.util.Arrays;

public class LruSimple {
    private final int CACHE_SIZE;
    private int[] dataArray;
    public LruSimple(int cacheSize){
        CACHE_SIZE=cacheSize;
        dataArray=new int[cacheSize];
    }
    public void put(int param){
        int temp = dataArray[0];
        dataArray[0]=param;
        for (int i = 1; i < CACHE_SIZE; i++) {
            int temp1 = dataArray[i];
            dataArray[i]=temp;
            temp=temp1;
        }
    }

    @Override
    public String toString() {
        return  Arrays.toString(dataArray);
    }
}
