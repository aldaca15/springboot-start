package com.modernjava;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
    public void sumOnlyPairsWithImperativeProgramming() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // filter and sum pairs, imperative
        int total = 0;
        for(Integer i : numbers) {
            if(i % 2 == 0) {
                total += i;
            }
        }
        System.out.println("Total should be 30 and is " + total);
        assertEquals(30, total); // Sum of 2, 4, 6, 8, and 10
    }

    @Test
    public void sumOnlyPairsWithDeclarativeProgramming() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int total = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(Integer:: intValue)
                .sum();
        System.out.println("Total should be 30 and is " + total);
        assertEquals(30, total); // Sum of 2, 4, 6, 8, and 10
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

    @Test
    public void nameListToUpperWithImperativeProgramming() {
        List<String> names = Arrays.asList("Ana", "Peter", "Louis", "Maria");
        List<String> upperCaseNames = new ArrayList<>();
        for (String name : names) {
            upperCaseNames.add(name.toUpperCase());
        }
        assertEquals(names.get(0).toUpperCase(), upperCaseNames.get(0)); // ANA
        assertEquals(names.get(names.size()-1).toUpperCase(), upperCaseNames.get(names.size()-1)); // MARIA
    }

    @Test
    public void nameListToUpperWithDeclarativeProgramming() {
        List<String> names = Arrays.asList("Ana", "Peter", "Louis", "Maria");
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        assertEquals(names.get(0).toUpperCase(), upperCaseNames.get(0)); // ANA
        assertEquals(names.get(names.size()-1).toUpperCase(), upperCaseNames.get(names.size()-1)); // MARIA
    }

    @Test
    public void findFirstNumberBiggerThan5_Imperative() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int firstBigger = 0;
        for(int number : numbers) {
            if(number > 5) {
                firstBigger = number;
                break;
            }
        }
        assertEquals(6, firstBigger);
    }

    @Test
    public void findFirstNumberBiggerThan5_Declarative() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer firstBigger = numbers.stream()
                .filter(number -> number > 5)
                .findFirst()
                .orElse(null);
        assertEquals(6, firstBigger); // Expected 6, and result is 6, so :)
    }

    @Test
    public void namesStartingWithM_Imperative() {
        List<String> names = Arrays.asList("Ana", "Mike", "Peter", "Louis", "Maria");
        int totalNames = 0;
        for (String name : names) {
            if(name.toLowerCase().startsWith("m")) {
                totalNames += 1;
            }
        }
        assertEquals(2, totalNames);
    }

    @Test
    public void namesStartingWithM_Declarative() {
        List<String> names = Arrays.asList("Ana", "Mike", "Peter", "Louis", "Maria");
        long totalNames = names.stream()
                .filter(name -> name.toLowerCase().startsWith("m"))
                .count();
        assertEquals(2, totalNames);
    }

    @Test
    public void orderNumbers_Imperative() {
        List<Integer> unsortedNumbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        Collections.sort(unsortedNumbers);
        assertEquals(1, unsortedNumbers.get(0));
        assertEquals(9, unsortedNumbers.get(unsortedNumbers.size()-1));
    }

    @Test
    public void orderNumbers_Declarative() {
        List<Integer> unsortedNumbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        List<Integer> sortedNumbers = unsortedNumbers.stream().sorted().collect(Collectors.toList());
        assertEquals(1, sortedNumbers.get(0));
        assertEquals(9, sortedNumbers.get(unsortedNumbers.size()-1));
    }

}
