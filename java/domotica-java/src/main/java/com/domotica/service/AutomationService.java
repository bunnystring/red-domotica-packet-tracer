package com.domotica.service;

import com.domotica.model.*;
import org.springframework.stereotype.Service;

/**
 * Replica en Java la misma logica de automatizacion configurada
 * en Packet Tracer mediante Conditions:
 *
 *  - HU2: si temperatura > 28C -> encender Aire Acondicionado
 *  - HU4: si se detecta movimiento -> encender Light
 *  - HU6: modo "Away" -> apaga Light y Aire Acondicionado
 *
 * Este servicio es el "cerebro" conceptual que representaria el backend
 * de la app movil, ya que Packet Tracer solo permite programar Python
 * de forma nativa dentro del simulador.
 *
 * @author Arlez Camilo Ceron Herrera
 */
@Service
public class AutomationService {

    private static final double TEMPERATURE_THRESHOLD = 28.0;

    /**
     * HU2 - Encendido automatico del A/C por temperatura.
     */
    public void evaluateTemperature(TemperatureSensor sensor, AirConditioner ac) {
        if (sensor.getCurrentTemperature() > TEMPERATURE_THRESHOLD) {
            ac.turnOn();
        } else {
            ac.turnOff();
        }
    }

    /**
     * HU4 - Encendido automatico de la light por movimiento.
     */
    public void evaluateMotion(MotionSensor sensor, Light light) {
        if (sensor.isMotionDetected()) {
            light.turnOn();
        }
    }

    /**
     * HU6 - Modo "Away": apaga light y A/C para ahorrar energia.
     */
    public void activateAwayMode(Light light, AirConditioner ac) {
        light.turnOff();
        ac.turnOff();
    }
}