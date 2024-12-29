package com.inheritancebyexample;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.AssertionErrors;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

public class GenericCalculatorTest {

    @Test
    public void divideOperationTest() throws Exception {
        Double expected = 10.0;
        Object actual = new GenericCalculator().divide(20D,2D);
        assertEquals(expected, actual);
    }

    @Test
    public void multiplyOperationTest() throws Exception {
        Double expected = 100.0;
        Object actual = new GenericCalculator().multiply(20D,5D);
        assertEquals(expected, actual);
    }

    @Test
    public void subtractOperationTest() throws Exception {
        Double expected = 15D;
        Object actual = new GenericCalculator().subtract(20D,5D);
        assertEquals(expected, actual);
    }

    @Test
    public void sumOperationTest() throws Exception {
        Double expected = 25D;
        Object actual = new GenericCalculator().sum(20D,5D);
        assertEquals(expected, actual);
    }

    @Test
    public void ArithmeticExceptionTest() {
        Exception ex = assertThrows(Exception.class, () -> {
            new GenericCalculator().divide(100D,"L");
        });
        AssertionErrors.assertTrue("Divide by zero exception", ex instanceof ClassCastException);
    }

}
