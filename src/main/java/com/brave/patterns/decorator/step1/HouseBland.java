package com.brave.patterns.decorator.step1;

/**
 * @author jbrave
 *
 *
 */
public class HouseBland extends Beverage{

    public HouseBland() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
