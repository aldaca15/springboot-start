package com.designpatterns.creational;

/**
 * Pizza store with single task (method) to order pizza
 */
public class PizzaStore {

    /**
     * Order pizza method, which will create a pizza for the chosen recipe
     * @param type param to create a pizza from factory
     * @return
     */
    public Pizza orderPizza(String type) {
        Pizza p = PizzaFactory.createPizza(type);
        p.prepare();
        p.bake();
        p.cut();

        return p;
    }


}
