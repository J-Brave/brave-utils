package com.brave.designpatterns.observepattern.step1;

import com.brave.designpatterns.observepattern.step1.interfaces.Observe;
import com.brave.designpatterns.observepattern.step1.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbrave
 */
public class WeatherData implements Subject {

    private List<Observe> observeList;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observeList = new ArrayList();
    }
    @Override
    public void registerObserver(Observe observe) {
        observeList.add(observe);
    }

    @Override
    public void removeObserver(Observe observe) {
        int i = observeList.indexOf(observe);
        if (i >= 0) {
            observeList.remove(observe);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observeList.size(); i++) {
            Observe observe = observeList.get(i);
            observe.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
