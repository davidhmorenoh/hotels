package com.management.hotels.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Objects;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        return Arrays.asList(enumClass.getEnumConstants()).contains(value);
    }

}