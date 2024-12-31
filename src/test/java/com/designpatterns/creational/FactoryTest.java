package com.designpatterns.creational;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FactoryTest {

    @Test
    public void pizzaFactoryTest() {
        PizzaStore ps = new PizzaStore();
        assertEquals("chicken", ps.orderPizza("chicken").getType());
        assertNotEquals("veggie", ps.orderPizza("chicken").getType());
        assertEquals("veggie", ps.orderPizza("veggie").getType());
        assertEquals("cheese", ps.orderPizza("cheese").getType());
    }

}
