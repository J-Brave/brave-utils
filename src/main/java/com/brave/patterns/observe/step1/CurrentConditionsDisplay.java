package com.brave.patterns.observe.step1;

import com.brave.patterns.observe.step1.interfaces.DisplayElement;
import com.brave.patterns.observe.step1.interfaces.Observe;
import com.brave.patterns.observe.step1.interfaces.Subject;

/**
 * @author jbrave
 * 布告板
 */
public class CurrentConditionsDisplay implements Observe, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity+ "% humidity");
    }
}
