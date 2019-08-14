package com.luckylee.enumvalidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * xx.
 *
 * @author Li Ning
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestBean {
    @EnumValidator(enumClazz = EnumTest.class)
    private String xxx;
}
