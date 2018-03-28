package com.luckylee.annotation;

import javassist.*;

/**
 * Created by lining on 2017/9/21.
 */
public class PointTest {

    public static  void  main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
            ClassPool pool= ClassPool.getDefault();

            CtClass ct = pool.get("com.luckylee.annotation.Point");
            CtMethod method = ct.getDeclaredMethod("move");

            method.insertBefore("{ System.out.println($1); System.out.println($2) ;}");

            Point o = (Point) ct.toClass().newInstance();
            o.move(1, 2);
        }
}
