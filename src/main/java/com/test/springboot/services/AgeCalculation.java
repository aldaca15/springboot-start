package com.test.springboot.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public class AgeCalculation {
    static Function<Integer, String> addZeros = x -> x < 10 ? "0" + x : String.valueOf(x);

    static TriFunction<Integer, Integer, Integer, LocalDate> parseDate =
            (day, month, year) ->
                    LocalDate.parse(year + "-" +
                    addZeros.apply(month) + "-" + addZeros.apply(day)); // Useful too, String.format("%02d ", month);
                    //LocalDate#of(int year, int month, int day) // Useful too, does not use addZeros Function

    static TriFunction<Integer, Integer, Integer, Integer> calculateAge =
            (day, month, year) -> Period.between(
                    parseDate.apply(day, month, year), LocalDate.now()
            ).getYears();

    public static Integer getAge(LocalDate birthDate) {
        return calculateAge.apply(birthDate.getDayOfMonth(), birthDate.getMonthValue(), birthDate.getYear());
    }

    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

}
