package com.predicates;

import com.test.springboot.entity.Apple;

public interface ApplePredicate {
    boolean test(Apple apple);
}
