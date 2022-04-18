package com.brave.designpatterns.strategypattern.step3.interfaces.impl;

import com.brave.designpatterns.strategypattern.step3.interfaces.QuackBehavior;

/**
 * @author jbrave
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("什么都不做，不会叫");
    }
}
