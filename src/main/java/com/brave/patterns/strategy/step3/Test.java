package com.brave.patterns.strategy.step3;

import com.brave.patterns.strategy.step3.interfaces.impl.FlyRocketPowered;
import com.brave.patterns.strategy.step3.interfaces.impl.Squeak;

/**
 * @author jbrave
 */
public class Test {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.performQuack();
        mallardDuck.performFly();
        mallardDuck.display();

        System.out.println("--*****--");

        ModelDuck modelDuck = new ModelDuck();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        modelDuck.setQuackBehavior(new Squeak());
        modelDuck.display();
        modelDuck.performFly();
        modelDuck.performQuack();
    }
}
