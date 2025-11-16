package com.designpatterns.creational;

public class PersonFactory {
    public static Person createPerson(String type) {
        Person p = null;
        if (type.equals("male")) {
            p = new Male();
        } else if (type.equals("female")) {
            p = new Female();
        }
        return p;
    }
}
interface Person {
    String wish(String msg);
}
class Male implements Person {
    @Override
    public String wish(String msg) {
        System.out.println(msg);
        return "Male wish: " + msg;
    }
}
class Female implements Person {
    @Override
    public String wish(String msg) {
        System.out.println(msg);
        return "Female wish: " + msg;
    }
}