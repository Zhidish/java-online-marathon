package com.softserve.itacademy.model;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {



    @Test
    void constraintViolationOnEmptyRoleName() {
        Role emptyRole = new Role();
        emptyRole.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(emptyRole);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void constraintViolationOnNullRoleName() {
        Role nullRoleName = new Role();
        nullRoleName.setName(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(nullRoleName);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void constraintViolationOnToShortRoleName() {
        Role shortRoleName = new Role();
        shortRoleName.setName("r");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(shortRoleName);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void constraintViolationOnToLargeRoleName() {
        Role largeRoleName = new Role();
        largeRoleName.setName("kdasfhjkhsdjfkhaskldfjkladshfljkadshfjkladshfjkladshlfjkhasdflasdfhasdlfd" +
                "jdaskfhljkasdfhjkladshfjkhadsjklfhadsjklasklfhjkladshfjkladhfjkdasjkfhdlkjfhaskjf" +
                "djksafhasdjfhjkladhfjkadshfjkhasdljkfhadsjkfhljkadsfhjkladshfjkladshfjfaslhfdsa" +
                "adsjflasjkdfhjkadshlfjkhadsjklfhasdklfjhdsjklfhadslfhjadshfjklasdhlfjkasdhkjfhadskjfhadsj" +
                "adsjkfhadsjklfhkjadhfkjladshfjkadhfjkadshfadsgasdfhadsjkfhljakdshfjkladshfjhaskdjlfhasdf" +
                "djkasfhljkdshfjklashdfjkadshflhadskjfhadsjhfjkdshfjkasdhljkadsfhjkladshfkdsahf");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(largeRoleName);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void constraintViolationOnIllegalSymbolsRoleName() {
        Role illegalSymbolsRoleName = new Role();
        illegalSymbolsRoleName.setName("role#~");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(illegalSymbolsRoleName);
        assertEquals(false, violations.isEmpty());
    }

    @Test
    void constraintViolationOnValidRoleName() {
        Role illegalSymbolsRoleName = new Role();
        illegalSymbolsRoleName.setName("User");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(illegalSymbolsRoleName);
        assertEquals(true, violations.isEmpty());
    }
}
