package com.inheritancebyexample;

public class GenericCalculator implements IOperations {

    @Override
    public Object multiply(Object o1, Object o2) throws Exception {
        if(!(o1 instanceof java.lang.Number) || !(o2 instanceof java.lang.Number)) {
            new Exception("Error: NaN");
        }
        return (Double) o1 * (Double) o2;
    }

    @Override
    public Object sum(Object o1, Object o2) throws Exception {
        if(!(o1 instanceof java.lang.Number) || !(o2 instanceof java.lang.Number)) {
            new Exception("Error: NaN");
        }
        return (Double) o1 + (Double) o2;
    }

    @Override
    public Object divide(Object o1, Object o2) throws Exception {
        if(!(o1 instanceof java.lang.Number) || !(o2 instanceof java.lang.Number)) {
            new Exception("Error: NaN");
        }
        return (Double) o1 / (Double) o2;
    }

    @Override
    public Object subtract(Object o1, Object o2) throws Exception {
        if(!(o1 instanceof java.lang.Number) || !(o2 instanceof java.lang.Number)) {
            new Exception("Error: NaN");
        }
        return (Double) o1 - (Double) o2;
    }
}
