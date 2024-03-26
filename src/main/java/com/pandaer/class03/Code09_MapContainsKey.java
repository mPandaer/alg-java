package com.pandaer.class03;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 研究一下 HashMap ContainsKey问题
 * SortMap的顺序问题
 */
public class Code09_MapContainsKey {


    /**
     * HashMap ContainsKey
     */
    @Test
    public void test01() {
        HashMap<String, String> map = new HashMap<>();
        map.put("okhttp","retrofit");
        System.out.println(map.containsKey("okhttp"));
    }

    /**
     * SortMap sort问题
     */
    @Test
    public void test02() {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        map.put(1,1);
        map.put(3,3);
        map.put(2,2);
        map.put(0,0);
        System.out.println(map);

        System.out.println(map.firstEntry());
    }
}
