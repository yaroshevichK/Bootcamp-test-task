package it.bootcamp.validation;

import it.bootcamp.TestApplication;
import it.bootcamp.dto.UserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {TestApplication.class})
class RoleValidatorConstraintTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private UserRequest userResponse;


    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void ValidatorIsLoaded() {
        assertNotNull(validator);
    }

    @Test
    void isNotValidName() {
        UserRequest userRequest = new UserRequest(
                "",
                "Ivanov",
                "Petrovich",
                "ipp@gmail.com",
                "ADMINISTRATOR"
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    void isNotValidSurname() {
        UserRequest userRequest = new UserRequest(
                "Ivan",
                "",
                "Petrovich",
                "ipp@gmail.com",
                "ADMINISTRATOR"
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    void isNotValidMiddleName() {
        UserRequest userRequest = new UserRequest(
                "Ivan",
                "Ivanov",
                "",
                "ipp@gmail.com",
                "ADMINISTRATOR"
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    void isNotValidEmail() {
        UserRequest userRequest = new UserRequest(
                "Ivan",
                "Ivanov",
                "Petrovich",
                "ippgmail.com",
                "ADMINISTRATOR"
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    void isNotValidRole() {
        UserRequest userRequest = new UserRequest(
                "Ivan",
                "Ivanov",
                "Petrovich",
                "ipp@gmail.com",
                "ADMINISTRAOR"
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }


    @Test
    void isAllValid() {
        UserRequest userRequest = new UserRequest(
                "Ivan",
                "Ivanov",
                "Petrovich",
                "ipp@gmail.com",
                "ADMINISTRATOR"
        );

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertTrue(violations.isEmpty());
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

}