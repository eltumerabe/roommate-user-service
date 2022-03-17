package com.roommate.domain.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface CustomValidator {

    @JsonIgnore
    default Optional<List<String>> getValidationErrors() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        return Optional.of(validator.validate(this))
                .filter(constraintViolations -> constraintViolations.size() > 0)
                .map(constraintViolations -> constraintViolations
                        .stream()
                        .map(customValidatorConstraintViolation -> customValidatorConstraintViolation
                                .getPropertyPath()
                                .toString() + " " + customValidatorConstraintViolation.getMessage())
                        .collect(Collectors.toList()));
    }
}
