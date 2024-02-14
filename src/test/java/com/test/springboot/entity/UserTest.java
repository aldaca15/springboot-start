package com.test.springboot.entity;

/* Previous imports called junit instead of jupiter, kept here as reference
import org.junit.Test;
import static org.junit.Assert.assertEquals;*/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

    @Test
    public void emailIsValid() {
        String email = "aliadame15@gmail.com";
        assertTrue(User.isUserEmailValid(email));
    }

    @Test
    public void emailNotValid() {
        String email = "aliadame";
        assertFalse(User.isUserEmailValid(email));
    }

}