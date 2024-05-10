package com.javagenerics;

/**
 * This is a class using generic methods
 * this is important because we are applying code reusability
 * instead of the verbose usage of overloading
 */
public class UsingGenerics1 {

    public static <T> T showItem(T item) {
        System.out.println("The item is: " + item.toString());
        return item;
    }

}
