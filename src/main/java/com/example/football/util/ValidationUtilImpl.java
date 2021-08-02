package com.example.football.util;

import org.springframework.stereotype.Component;

import javax.validation.Validation;

@Component
public class ValidationUtilImpl implements ValidationUtil {
    private final javax.validation.Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }

}