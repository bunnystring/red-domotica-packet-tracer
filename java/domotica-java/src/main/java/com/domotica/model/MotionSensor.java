package com.domotica.model;

/**
 * Sensor de movimiento. Alimenta la regla de HU4.
 *
 * @author Arlez Camilo Ceron Herrera
 */
public class MotionSensor {
    private boolean motionDetected;

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void setMotionDetected(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }
}