package com.javagenerics;

/***
 * This sample class contains three simple methods
 * with the same functionality but dealing with overloading
 */
public class NotUsingGenerics {

    public static String sum(Object num1, Object num2) {
        int result = (Integer) num1 + (Integer) num2;
        String resStr = "Result is " + result;
        System.out.println(resStr);
        return resStr;
    }

    public static String sum(int num1, int num2) {
        int result = num1 + num2;
        String resStr = "Result has integer " + result;
        System.out.println(resStr);
        return resStr;
    }

    public static String sum(float num1, float num2) {
        float result = num1 + num2;
        String resStr = "Result has float " + result;
        System.out.println(resStr);
        return resStr;
    }

    public static String sum(double num1, double num2) {
        double result = num1 * num2;
        String resStr = "Result has double " + result;
        System.out.println(resStr);
        return resStr;
    }

}
