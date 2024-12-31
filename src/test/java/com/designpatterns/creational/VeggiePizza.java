package com.designpatterns.creational;

public class VeggiePizza implements Pizza {

    public String pizzaType = null;

    public VeggiePizza(String type) {
        this.pizzaType = type;
    }

    @Override
    public String getType() {
        return pizzaType;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Veggie Pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Veggie Pizza");

    }

    @Override
    public void cut() {
        System.out.println("Cutting Veggie Pizza");

    }

}