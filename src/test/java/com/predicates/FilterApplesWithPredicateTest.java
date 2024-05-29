package com.predicates;

import com.test.springboot.entity.Apple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Following test class is an exercise to check encapsulation as a way to filter things
 * Note: There are some architecture issues on this classes approach as code is not able
 * to filter golden apples for instance, for which a refactoring might be needed
 */
public class FilterApplesWithPredicateTest {

    @Test
    public void filterByHeavyWeight() {
        List<Apple> someApples = genericAppleList();
        List<Apple> heavyApples = this.filterApples(someApples, new AppleHeavyWeightPredicate());
        assertTrue(heavyApples.size() == 2); // Only 2 apples are over 150 of weight
    }

    @Test
    public void filterByGreenColor() {
        List<Apple> someApples = genericAppleList();
        List<Apple> redApples = this.filterApples(someApples, new AppleGreenColorPredicate());
        assertTrue(redApples.size() == 4); // 4 apples out of 6 are green
    }

    // Additional test that implements red filter by using an anonymous class
    @Test
    public void filterByRedColor() {
        List<Apple> someApples = genericAppleList();
        List<Apple> redApples = this.filterApples(someApples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equalsIgnoreCase(apple.getColor());
            }
        });
        assertTrue(redApples.size() == 2); // 2 apples out of 6 are red
    }

    public List<Apple> genericAppleList() {
        List<Apple> appleQAline = new ArrayList<>();
        Apple apple1 = new Apple("red", 120);
        appleQAline.add(apple1);
        Apple apple2 = new Apple("GREEN", 140);
        appleQAline.add(apple2);
        Apple apple3 = new Apple("Red", 150);
        appleQAline.add(apple3);
        Apple apple4 = new Apple("green", 160);
        appleQAline.add(apple4);
        Apple apple5 = new Apple("Green", 180);
        appleQAline.add(apple5);
        Apple apple6 = new Apple("green", 140);
        appleQAline.add(apple6);
        return appleQAline;
    }

    /**
     * This method applies filtering by an specific predicate implementation
     * @param inventory list of elements to filter out
     * @param p Predicate with the filter rule (weight|color) applied by encapsulation
     * @return list of matching elements
     */
    public List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple element : inventory) {
            if(p.test(element)) {
                filteredApples.add(element);
            }
        }
        return filteredApples;
    }

}
