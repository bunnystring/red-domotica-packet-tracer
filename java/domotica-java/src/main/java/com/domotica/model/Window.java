package com.domotica.model;

/**
 * Representa la ventana. Cubre HU5 (control remoto de ventilacion).
 * "encendido" se interpreta como "abierta".
 *
 * @author Arlez Camilo Ceron Herrera
 */
public class Window extends Device {
    public Window(String id, String name) {
        super(id, name);
    }

    public String getWindowStatus() {
        return isOn() ? "OPEN" : "CLOSED";
    }
}