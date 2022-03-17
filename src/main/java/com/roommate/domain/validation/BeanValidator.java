package com.roommate.domain.validation;

import org.springframework.stereotype.Component;

@Component
public class BeanValidator {
    public BeanValidator() {
    }

    public <T extends CustomValidator> T dtoValidation(final T customValidator) {
        customValidator.getValidationErrors().ifPresent(errors -> {
            //throw Exception();
        });
        return customValidator;
    }
}
