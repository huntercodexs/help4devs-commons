package com.huntercodexs.demo.annotation.valitation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonValidator implements ConstraintValidator<Help4DevsValidationAnnotation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("isValid is running");
        return value != null && value.length() > 3;
    }
}
