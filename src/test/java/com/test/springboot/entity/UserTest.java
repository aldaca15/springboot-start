package com.test.springboot.entity;

/*import org.junit.Test;
import static org.junit.Assert.assertEquals;*/

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void emailIsValid() throws Exception {
        String email = "aliadame@gmail.com";
        assertThat(User.isUserEmailValid(email));
    }

    @Test
    public void emailNotValid() {
        String email = "aliadame";
        assertEquals(false, User.isUserEmailValid(email));
    }

}