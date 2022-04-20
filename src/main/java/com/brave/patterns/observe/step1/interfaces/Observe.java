package com.brave.patterns.observe.step1.interfaces;

/**
 * @author jbrave
 * 观察者
 */
public interface Observe {
    void update(float temp, float humidity, float pressure);
}
