package com.luckylee.enumvalidator;

/**
 * 枚举 测试.
 *
 * @author Li Ning
 * @since 1.0
 */
public enum EnumTest {
    W("w"), E("E");
    private String v;

    EnumTest(String v) {
        this.v = v;
    }

    public final String value() {
        return v;
    }
}
