package com.javagenerics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotUsingGenericsTest {

    @Test
    public void testExceptionWhenAddingNotSupportedDataTypes() throws Exception {
        Exception exception = assertThrows(ClassCastException.class, () -> {
            Object ob1 = 2;
            Object ob2 = 2.5D;
            // Error on runtime, while trying to add an int with a double
            NotUsingGenerics.sum(ob1, ob2);
        });

        String expectedMessage = "java.lang.Double cannot be cast to class";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWithDifferentDataTypesByOverloading() {
        // In this test we are using methods overload to get the sum of two different values
        String resultInt = NotUsingGenerics.sum(3, 9);
        String resultFloat = NotUsingGenerics.sum(3.01f, 9);
        assertTrue(resultInt.contains("Result has integer"));
        assertTrue(resultFloat.contains("Result has float"));
    }

    @Test
    public void testWithDifferentDataTypesWithExceptionDueToDouble() {
        // In this test we are using overload with int and double
        String resultInt = NotUsingGenerics.sum(3, 9);
        String resultFloat = NotUsingGenerics.sum(3.15615161D, 9.76464D);
        assertTrue(resultInt.contains("Result has integer"));
        assertTrue(resultFloat.contains("Result has double"));
    }

}
