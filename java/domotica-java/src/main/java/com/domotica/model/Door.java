package com.domotica.model;

/**
 * Representa la puerta principal. Cubre HU3 (monitoreo de estado).
 * En este caso "encendido" se interpreta como "abierta".
 *
 * @author Arlez Camilo Ceron Herrera
 */
public class Door extends Device {
    public Door(String id, String name) {
        super(id, name);
    }

    public String getDoorStatus() {
        return isOn() ? "OPEN" : "CLOSED";
    }
}