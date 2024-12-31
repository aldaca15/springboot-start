package com.designpatterns.creational;

/**
 * Inteface on factory implementation for pizzaStore
 */
public interface Pizza {

    void prepare();

    void bake();

    void cut();

    String getType();
}
