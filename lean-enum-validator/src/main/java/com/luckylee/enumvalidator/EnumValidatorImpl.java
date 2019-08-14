package com.luckylee.enumvalidator;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验.
 *
 * @author Li Ning
 * @since 1.0
 */

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private List<String> valueList  = new ArrayList<>();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return valueList.contains(value.toUpperCase());
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {

        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        @SuppressWarnings("rawtypes") Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }

    }

}
