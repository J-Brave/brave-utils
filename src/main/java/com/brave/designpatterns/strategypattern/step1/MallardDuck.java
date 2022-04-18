package com.brave.designpatterns.strategypattern.step1;

/**
 * @author jbrave
 * 绿头鸭
 */
public class MallardDuck extends Duck{
    @Override
    public void display() {
        System.out.println("外观是绿头");
    }
}
