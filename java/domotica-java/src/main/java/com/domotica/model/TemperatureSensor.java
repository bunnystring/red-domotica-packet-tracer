package com.domotica.model;

/**
 * Sensor de temperatura. Alimenta la regla de HU2.
 *
 * @author Arlez Camilo Ceron Herrera
 */
public class TemperatureSensor {
    private double currentTemperature;

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}