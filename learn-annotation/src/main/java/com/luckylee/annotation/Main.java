package com.luckylee.annotation;

import java.lang.reflect.Field;

/**
 * Created by lining on 2017/8/30.
 */
public class Main {
    @CreateInstance
    private Person lee;
    @CreateInstance
    private Person lee1;
    @CreateInstance
    private Person lee2;

    int age = 28;
    private static Main mainInstance = new Main();

    public void print() throws IllegalAccessException {
        final Field[] declaredFields = Main.class.getDeclaredFields();
        for (Field field : declaredFields) {
            boolean annotationPresent = field.isAnnotationPresent(CreateInstance.class);
            if (annotationPresent) {
                field.setAccessible(true);
                field.set(getInstance(), new Person("lee", age));
                age++;
            }
        }
        System.out.println(lee.getAge());
        System.out.println(lee1.getAge());
        System.out.print(lee2.getAge());

    }

    public static Main getInstance() {
        return mainInstance;
    }

    public static void main(String[] args) {
        try {
            getInstance().print();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
