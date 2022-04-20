package com.brave.patterns.strategy.step3.interfaces.impl;

import com.brave.patterns.strategy.step3.interfaces.FlyBehavior;

/**
 * @author jbrave
 */
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("火箭飞行");
    }
}
