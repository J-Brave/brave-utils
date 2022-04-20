package com.brave.patterns.observe.step2;

/**
 * @author jbrave
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(90, 40, 34.f);

    }
}
