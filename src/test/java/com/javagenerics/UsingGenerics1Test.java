package com.javagenerics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsingGenerics1Test {

    @Test
    public void testPrintingValueWithDifferentTypes() {
        String value1 = UsingGenerics1.showItem(new Integer(15)).toString();
        String value2 = UsingGenerics1.showItem(15.01D).toString();
        String value3 = UsingGenerics1.showItem("A string");

        assertTrue(new Integer(15).toString().contains(value1));
        assertTrue(new Double(15.01D).toString().contains(value2));
        assertTrue(new String("A string").toString().contains(value3));
    }

}
