package com.luckylee.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaTest {
    public static void main(String args[]) {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (o) -> o.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }

    private static void filter(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name)))
                .forEach(
                        System.out::println
                );
    }
}
