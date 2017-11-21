package com.hgwxr.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class LruLinkedHashMapTest {
    @Test
    public void lruTest(){
        LruLinkedHashMap<String, Integer> map = new LruLinkedHashMap<>(3);
        int[] params = {4, 3, 4, 2, 3, 1, 4, 2};
        for (int param : params) {
            map.put(param+"",param);
            System.out.println(map.toString());
        }
    }
    @Test
    public void lruTest1(){
        LruSimple  map = new LruSimple(3);
        int[] params = {4, 3, 4, 2, 3, 1, 4, 2};
        for (int param : params) {
            map.put(param);
            System.out.println(map.toString());
        }
    }

}