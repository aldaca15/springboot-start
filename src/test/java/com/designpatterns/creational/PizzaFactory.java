package com.designpatterns.creational;

/**
 * Pizza factory to create diff. pizzas depending the type
 */
public class PizzaFactory {

    /**
     * Create pizza method for factory
     * @param type which can be cheese, chicken, veggie or other
     * @return
     */
    public static Pizza createPizza(String type) {
        Pizza p = null;

        if (type.equals("cheese")) {
            p = new CheesePizza("cheese");
        } else if (type.equals("chicken")) {
            p = new ChickenPizza("chicken");
        } else if (type.equals("veggie")) {
            p = new VeggiePizza("veggie");
        }

        return p;
    }

}
