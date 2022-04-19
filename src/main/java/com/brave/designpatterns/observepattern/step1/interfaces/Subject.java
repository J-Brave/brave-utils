package com.brave.designpatterns.observepattern.step1.interfaces;

/**
 * @author jbrave
 * 主题
 */
public interface Subject {
    /**
     * 观察者注册
     * @param observe
     */
    void registerObserver(Observe observe);
    /**
     * 观察者删除
     * @param observe
     */
    void removeObserver(Observe observe);
    /**
     * 通知观察者
     */
    void notifyObservers();
}
