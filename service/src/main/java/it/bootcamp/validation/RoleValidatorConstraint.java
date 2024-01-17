package it.bootcamp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleValidatorConstraint implements ConstraintValidator<RoleValidator, String> {

    Set<String> values;

    @Override
    public void initialize(RoleValidator constraintAnnotation) {
        values = Stream.of(constraintAnnotation.role().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return values.contains(value);
    }
}