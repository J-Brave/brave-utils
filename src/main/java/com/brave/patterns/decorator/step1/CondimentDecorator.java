package com.brave.patterns.decorator.step1;

/**
 * @author jbrave
 * 调料装饰者
 *
 * 必须让装饰者能够取代被装饰者，所以装饰者（CondimentDecorator）扩展自被装饰者（Beverage）
 */
public abstract class CondimentDecorator extends Beverage{
    @Override
    public double cost() {
        return 0;
    }

    /**
     * 所有的调料装饰者都必须要重新实现
     * @return
     */
    public abstract String getDescription();
}
