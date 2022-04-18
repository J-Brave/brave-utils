package com.brave.designpatterns.strategypattern.step3.interfaces.impl;

import com.brave.designpatterns.strategypattern.step3.interfaces.QuackBehavior;

/**
 * @author jbrave
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("橡皮鸭子吱吱叫");
    }
}
