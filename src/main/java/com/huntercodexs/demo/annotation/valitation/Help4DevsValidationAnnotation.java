package com.huntercodexs.demo.annotation.valitation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PersonValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Help4DevsValidationAnnotation {
    String message() default "invalid name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
