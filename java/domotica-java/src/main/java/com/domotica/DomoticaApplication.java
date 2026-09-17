package com.domotica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion.
 * Este backend representa, en Java, la misma logica de automatizacion
 * implementada dentro de Packet Tracer mediante Conditions (HU2, HU4),
 * ya que el simulador no permite ejecutar Java de forma nativa.
 */
@SpringBootApplication
public class DomoticaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DomoticaApplication.class, args);
    }
}
