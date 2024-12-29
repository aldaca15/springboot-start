package com.modernjava;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The following unit tests presents the differences between
 * imperative vs declarative Java programming, by example
 */
public class ImperativeVsDeclarativeTest {

    public static final int sumResultFrom1To5 = 15;
    public static final String oneToFiveStr = "12345";

    @Test
    public void sumWithImperativeProgramming() {
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            sum += i;
        }
        assertEquals(sumResultFrom1To5, sum);
    }

    @Test
    public void sumWithDeclarativeProgramming() {
        IntStream numbers = IntStream.of(1,2,3,4,5); // This line is similar to the one below
        //IntStream numbers = IntStream.rangeClosed(1,5);
        int sum = numbers.sum();
        assertEquals(sumResultFrom1To5, sum);
    }

    @Test
    public void stringWithImperativeProgramming() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i);
        }
        assertEquals(oneToFiveStr, sb.toString());
    }

    @Test
    public void stringWithDeclarativeProgramming() {
        IntStream numbers = IntStream.rangeClosed(1,5);
        StringBuilder sb = new StringBuilder();
        numbers.forEach(sb::append);
        assertEquals(oneToFiveStr, sb.toString());
    }
}
