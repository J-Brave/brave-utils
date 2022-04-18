package com.brave.designpatterns.strategypattern.step3.interfaces.impl;

import com.brave.designpatterns.strategypattern.step3.interfaces.FlyBehavior;

/**
 * @author jbrave
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("什么都不做，不会飞");
    }
}
