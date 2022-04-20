package com.brave.patterns.strategy.step3;

import com.brave.patterns.strategy.step3.interfaces.FlyBehavior;
import com.brave.patterns.strategy.step3.interfaces.QuackBehavior;

/**
 * @author jbrave
 * 设计原则：
 * 1-分开变化和不会变化的部分
 * 2-针对接口编程（针对超类型编程）
 * 3-多用组合，少用继承
 *
 */
public abstract class Duck {
    public QuackBehavior quackBehavior;
    public FlyBehavior flyBehavior;

    /**
     * 鸭子对象不亲自处理呱呱叫行为，而是委托给quackBehavior饮用的对象
     */
    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void swim() {
        System.out.println("鸭子游泳");
    }
    public abstract void display();

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }
}
