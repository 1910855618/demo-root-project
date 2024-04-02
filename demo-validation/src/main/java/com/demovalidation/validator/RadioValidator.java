package com.demovalidation.validator;

import com.demovalidation.annotation.Radio;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RadioValidator implements ConstraintValidator<Radio, String> {
    private List<String> list;

    @Override
    public void initialize(Radio constraintAnnotation) {
        list = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return list.contains(Optional.ofNullable(value).orElse(""));
    }
}
