package com.brave.patterns.decorator.step1;

/**
 * @author jbrave
 *
 * 浓缩咖啡
 *
 * 扩展自（Beverage），因为Espresso是一种饮料
 */
public class Espresso extends Beverage{

    /**
     * 饮料描述
     */
    public Espresso() {
        description = "Espresso";
    }

    /**
     * Espresso的价钱
     * @return
     */
    @Override
    public double cost() {
        return 1.99;
    }
}
