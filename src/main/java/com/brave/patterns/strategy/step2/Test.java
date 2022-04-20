package com.brave.patterns.strategy.step2;

/**
 * @author jbrave
 */
public class Test {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.fly();
        mallardDuck.display();
        System.out.println("--*****---");
        RedHeadDuck redHeadDuck = new RedHeadDuck();
        redHeadDuck.quack();
        redHeadDuck.swim();
        redHeadDuck.fly();
        redHeadDuck.display();
    }
}
