package com.brave.designpatterns.strategypattern.step3.interfaces.impl;

import com.brave.designpatterns.strategypattern.step3.interfaces.QuackBehavior;

/**
 * @author jbrave
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("实现鸭子呱呱叫");
    }
}
