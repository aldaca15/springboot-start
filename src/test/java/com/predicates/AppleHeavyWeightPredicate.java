package com.predicates;

import com.test.springboot.entity.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

    /**
     * This method implements a boolean method to filter apples based on weight
     * by using encapsulation of the ApplePredicate interface
     * to ease the process of performing operations with apples
     * @see AppleGreenColorPredicate encapsulation implementing eval by color
     * @param apple with an object to eval for a condition
     * @return boolean if apple pass the QA threshold (at least matching condition)
     */
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
