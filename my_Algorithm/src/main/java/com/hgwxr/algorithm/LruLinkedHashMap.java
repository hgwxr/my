package com.hgwxr.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruLinkedHashMap<K,V> extends LinkedHashMap<K,V>{
    private  final int CACHE_SIZE;

    public LruLinkedHashMap(int cacheSize){
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE=cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()>CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
