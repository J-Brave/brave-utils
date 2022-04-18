package com.brave.designpatterns.strategypattern.step3;

import com.brave.designpatterns.strategypattern.step3.interfaces.impl.FlyWithWings;
import com.brave.designpatterns.strategypattern.step3.interfaces.impl.Quack;

/**
 * @author jbrave
 */
public class ModelDuck extends Duck{
    public ModelDuck() {
        //多态
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
    @Override
    public void display() {
        System.out.println("模型");
    }
}
