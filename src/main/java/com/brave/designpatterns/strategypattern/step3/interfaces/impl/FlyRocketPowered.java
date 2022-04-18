package com.brave.designpatterns.strategypattern.step3.interfaces.impl;

import com.brave.designpatterns.strategypattern.step3.interfaces.FlyBehavior;

/**
 * @author jbrave
 */
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("火箭飞行");
    }
}
