package it.bootcamp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static it.bootcamp.util.DataService.MESSAGE_ROLE;

@Documented
@Constraint(validatedBy = RoleValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull
public @interface RoleValidator {

    Class<? extends Enum<?>> role();

    String message() default MESSAGE_ROLE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}