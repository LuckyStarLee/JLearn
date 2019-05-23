package com.luckylee.test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

public class AviatorTest {

    public static void main(String[] args) {
        Map<String, Object> param = new HashMap<>();
        param.put("a", 1);
        param.put("b", 2);
        String express = "a*2==b";
        Expression expression = AviatorEvaluator.compile(express);
        Boolean f = (Boolean) expression.execute(param);
        System.out.println(f);
    }
}
