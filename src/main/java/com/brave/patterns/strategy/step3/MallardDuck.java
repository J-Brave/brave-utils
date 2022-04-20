package com.brave.patterns.strategy.step3;

import com.brave.patterns.strategy.step3.interfaces.impl.FlyWithWings;
import com.brave.patterns.strategy.step3.interfaces.impl.Quack;

/**
 * @author jbrave
 */
public class MallardDuck extends Duck{
    public MallardDuck() {
        //多态
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
    @Override
    public void display() {
        System.out.println("外观是绿头");
    }
}
