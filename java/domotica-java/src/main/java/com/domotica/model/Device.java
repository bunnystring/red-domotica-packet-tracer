package com.domotica.model;

/**
 * Clase base para todos los dispositivos domoticos.
 *
 * @author Arlez Camilo Ceron Herrera
 */
public abstract class Device {

    private String id;
    private String name;
    private boolean on;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
        this.on = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return on;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public String getStatus() {
        return on ? "ON" : "OFF";
    }
}