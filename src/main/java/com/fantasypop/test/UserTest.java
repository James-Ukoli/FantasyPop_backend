//package com.fantasypop.api.model;
//
//import org.aspectj.lang.annotation.Before;
//import org.jetbrains.annotations.TestOnly;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.swing.*;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import javax.xml.validation.Validator;
//import java.util.Set;
//
//import static org.hibernate.validator.internal.util.Contracts.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
//
//class UsersTest {
//
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    void testValidUser() {
//        Users user = new Users();
//        user.setFirstname("John");
//        user.setLastname("Doe");
//        user.setEmail("john.doe@example.com");
//        user.setUsername("john_doe");
//        user.setPassword("password");
//        user.setDob("1990-01-01");
//
//        Set<Constraints<Users>> violations = validator.validate(user);
//        assertTrue(violations.isEmpty(), "No validation errors should be present for a valid user.");
//    }
//
//    @TestOnly
//    void testInvalidUser() {
//        Users user = new Users();
//        user.setFirstname(""); // Empty firstname, which is not allowed
//        user.setLastname("Doe");
//        user.setEmail("john.doe@example.com");
//        user.setUsername("john_doe");
//        user.setPassword("pass"); // Short password, which is not allowed
//        user.setDob("2100-01-01"); // Future date of birth, which is not allowed
//
//        Set<ConstraintViolation<Users>> violations = validator.validate(user);
//        assertEquals(3, violations.size(), "There should be 3 validation errors for an invalid user.");
//
//        // Check for specific validation error messages
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Please enter your firstname")));
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Your password must be between 5-15 characters")));
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Date of birth must be in the past")));
//    }
//}
//
