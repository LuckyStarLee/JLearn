package com.luckylee.pattern.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaPattern {

    /**
     * * 0次或者多次
     * + 1次或者多次
     * ? 0次或者1次
     * {n} 恰好n次
     * {n,m} 从n次到m次
     */
    @Test
    public void functionDemo_4() {
        String str = "da jia hao,ming tian bu fang jia!";
        String regex = "\\b[a-z]{3}\\b";
        //1,将正则封装成对象。
        Pattern p = Pattern.compile(regex);
        //2, 通过正则对象获取匹配器对象。
        Matcher m = p.matcher(str);
        //使用Matcher对象的方法对字符串进行操作。
        //既然要获取三个字母组成的单词
        //查找。 find();
        //System.out.println(str);
        while (m.find()) {
            System.out.println(m.group());//获取匹配的子序列
            System.out.println(m.start() + ":" + m.end());
        }
    }
}
