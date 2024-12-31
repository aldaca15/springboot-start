package com.designpatterns.creational;

public class ChickenPizza implements Pizza {

    public String pizzaType = null;

    public ChickenPizza(String type) {
        this.pizzaType = type;
    }

    @Override
    public String getType() {
        return pizzaType;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Chicken Pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicken Pizza");

    }

    @Override
    public void cut() {
        System.out.println("Cutting Chicken Pizza");

    }

}