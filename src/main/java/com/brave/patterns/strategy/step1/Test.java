package com.brave.patterns.strategy.step1;

/**
 * @author jbrave
 */
public class Test {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.display();
        System.out.println("--*****--");
        RedHeadDuck redHeadDuck = new RedHeadDuck();
        redHeadDuck.quack();
        redHeadDuck.swim();
        redHeadDuck.display();
    }
}
