package com.test.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class MyComponentBean implements MyComponent {
    @Override
    public void printSomething() {
        System.out.println("Imprimiendo algo de MyComponentBean");
    }
}
