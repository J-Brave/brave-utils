package com.brave.designpatterns.strategypattern.step3.interfaces.impl;

import com.brave.designpatterns.strategypattern.step3.interfaces.FlyBehavior;

/**
 * @author jbrave
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("实现鸭子飞行动作");
    }
}
