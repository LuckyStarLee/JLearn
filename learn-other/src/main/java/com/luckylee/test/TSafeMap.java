package com.luckylee.test;

import java.util.*;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-10 15:05
 **/
public class TSafeMap {
    public static void main(String[] args) {
        TreeMap<String,String> emails=new TreeMap<>();
        emails.put("0","1");
        emails.put("2","2");
        emails.put("1","2");
        emails.put("5","5");
        Set<Map.Entry<String,String>> s= emails.entrySet();
        for (Map.Entry<String,String> entry: s) {
            System.out.println(entry.getKey()+"---->"+entry.getValue());
        }

    }
}
