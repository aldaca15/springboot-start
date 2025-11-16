package com.designpatterns.creational;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryTest {

    @Test
    public void pizzaFactoryTest() {
        PizzaStore ps = new PizzaStore();
        assertEquals("chicken", ps.orderPizza("chicken").getType());
        assertNotEquals("veggie", ps.orderPizza("chicken").getType());
        assertEquals("veggie", ps.orderPizza("veggie").getType());
        assertEquals("cheese", ps.orderPizza("cheese").getType());
    }

    @Test
    public void personFactoryTest() {
        assertTrue(PersonFactory.createPerson("female").wish("become an artist").contains("Female wish:"));
        assertTrue(PersonFactory.createPerson("male").wish("become an stuntman").contains("Male wish:"));
    }

}
