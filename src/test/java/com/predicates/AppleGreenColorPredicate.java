package com.predicates;

import com.test.springboot.entity.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {

    /**
     * This method implements a boolean method to filter apples based on color
     * by using encapsulation of the ApplePredicate interface
     * to ease the process of performing operations with apples
     * @see AppleHeavyWeightPredicate encapsulation implementing eval by weight
     * @param apple with an object to eval for a condition
     * @return boolean if apple pass the QA threshold (at least matching condition)
     */
    @Override
    public boolean test(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }
}
