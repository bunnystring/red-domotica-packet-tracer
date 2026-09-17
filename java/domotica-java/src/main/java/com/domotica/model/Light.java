package com.domotica.model;

/**
 * Representa la lampara del hogar. Cubre HU1 (control remoto)
 * y HU4 (encendido automatico por movimiento).
 *
 * @author Arlez Camilo Ceron Herrera
 */
public class Light extends Device {
    public Light(String id, String name) {
        super(id, name);
    }
}