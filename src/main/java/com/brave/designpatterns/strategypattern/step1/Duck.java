package com.brave.designpatterns.strategypattern.step1;

/**
 * @author jbrave
 * 鸭子超类
 */
public abstract class Duck {
    /**
     * 所有鸭子都会呱呱叫
     */
    public void quack() {
        System.out.println("鸭子呱呱叫");
    }
    /**
     * 所有鸭子都会游泳
     */
    public void swim() {
        System.out.println("鸭子游泳");
    }
    /**
     * 每一种鸭子外观不同，所以用抽象方法
     */
    public abstract void display();
}
