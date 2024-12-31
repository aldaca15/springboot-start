package com.designpatterns.creational;

public class CheesePizza implements Pizza {

    public String pizzaType = null;

    public CheesePizza(String type) {
        this.pizzaType = type;
    }

    @Override
    public String getType() {
        return pizzaType;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Cheese Pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Cheese Pizza");
    }

    @Override
    public void cut() {
        System.out.println("Cutting Cheese Pizza");
    }

}