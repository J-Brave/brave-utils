package com.brave.patterns.strategy.step2;

/**
 * @author jbrave
 * 鸭子超类 让鸭子飞起来
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
    /**
     * 让所有鸭子飞起来
     */
    public void fly(){
        System.out.println("鸭子飞起来");
    }
}
