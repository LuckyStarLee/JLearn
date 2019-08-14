package com.luckylee.enumvalidator;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * test.
 *
 * @author Li Ning
 * @since 1.0
 */
public class EnumMain {
    public static void main(String[] args) {
        TestBean b = new TestBean();
        b.setXxx("1");
        //        b.setXxx("W");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<TestBean>> constraintViolations = validator.validate(b);
        System.out.println(constraintViolations);
    }
}
