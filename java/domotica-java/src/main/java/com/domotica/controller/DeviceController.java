package com.domotica.controller;

import com.domotica.model.*;
import com.domotica.service.AutomationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Expone los endpoints que representarian lo que el celular
 * enviaria al Home Gateway en el escenario real de Packet Tracer.
 *
 * @author Arlez Camilo Ceron Herrera
 */
@RestController
@RequestMapping("/api/devices")
@Tag(name = "Smart Home Devices", description = "Endpoints to control and automate the home devices simulated in Packet Tracer")
public class DeviceController {

    private final AutomationService automationService = new AutomationService();

    // Estado en memoria de los dispositivos (simula la red domotica)
    private final Light light = new Light("IoT2", "Living Room Light");
    private final AirConditioner ac = new AirConditioner("IoT3", "Air Conditioner");
    private final Door door = new Door("IoT0", "Main Door");
    private final Window window = new Window("IoT1", "Living Room Window");
    private final TemperatureSensor temperatureSensor = new TemperatureSensor();
    private final MotionSensor motionSensor = new MotionSensor();

    // ---- HU1: control remoto de la luz ----
    @Operation(summary = "Turn the light on or off (HU1)", description = "Controls the living room light remotely, as if sent from the mobile app.")
    @PostMapping("/light/{action}")
    public Map<String, String> controlLight(
            @Parameter(description = "Action to perform: on or off") @PathVariable String action) {
        if (action.equalsIgnoreCase("on")) {
            light.turnOn();
        } else {
            light.turnOff();
        }
        return response("Light", light.getStatus());
    }

    // ---- HU2: temperatura -> A/C automatico ----
    @Operation(summary = "Update the temperature reading (HU2)", description = "Simulates a new reading from the temperature sensor. If it exceeds 28C, the A/C turns on automatically.")
    @PostMapping("/temperature/{value}")
    public Map<String, String> updateTemperature(
            @Parameter(description = "New temperature value in Celsius") @PathVariable double value) {
        temperatureSensor.setCurrentTemperature(value);
        automationService.evaluateTemperature(temperatureSensor, ac);
        return response("AirConditioner", ac.getStatus());
    }

    // ---- HU3: estado de la puerta ----
    @Operation(summary = "Get door status (HU3)", description = "Returns whether the main door is open or closed.")
    @GetMapping("/door")
    public Map<String, String> getDoorStatus() {
        return response("Door", door.getDoorStatus());
    }

    @Operation(summary = "Open or close the door (HU3)")
    @PostMapping("/door/{action}")
    public Map<String, String> controlDoor(
            @Parameter(description = "Action to perform: open or close") @PathVariable String action) {
        if (action.equalsIgnoreCase("open")) {
            door.turnOn();
        } else {
            door.turnOff();
        }
        return response("Door", door.getDoorStatus());
    }

    // ---- HU4: movimiento -> luz automatica ----
    @Operation(summary = "Update motion detection (HU4)", description = "Simulates the motion sensor. If motion is detected, the light turns on automatically.")
    @PostMapping("/motion/{detected}")
    public Map<String, String> updateMotion(
            @Parameter(description = "true if motion was detected, false otherwise") @PathVariable boolean detected) {
        motionSensor.setMotionDetected(detected);
        automationService.evaluateMotion(motionSensor, light);
        return response("Light", light.getStatus());
    }

    // ---- HU5: control remoto de la ventana ----
    @Operation(summary = "Open or close the window (HU5)")
    @PostMapping("/window/{action}")
    public Map<String, String> controlWindow(
            @Parameter(description = "Action to perform: open or close") @PathVariable String action) {
        if (action.equalsIgnoreCase("open")) {
            window.turnOn();
        } else {
            window.turnOff();
        }
        return response("Window", window.getWindowStatus());
    }

    // ---- HU6: modo Away ----
    @Operation(summary = "Activate Away mode (HU6)", description = "Turns off the light and the A/C at once to save energy when leaving home.")
    @PostMapping("/away-mode")
    public Map<String, String> activateAwayMode() {
        automationService.activateAwayMode(light, ac);
        Map<String, String> res = new HashMap<>();
        res.put("Light", light.getStatus());
        res.put("AirConditioner", ac.getStatus());
        return res;
    }

    // ---- Estado general de todos los dispositivos ----
    @Operation(summary = "Get the general status of all devices", description = "Returns the current state of every device in the simulated home network.")
    @GetMapping("/status")
    public Map<String, String> getGeneralStatus() {
        Map<String, String> status = new HashMap<>();
        status.put("Light", light.getStatus());
        status.put("AirConditioner", ac.getStatus());
        status.put("Door", door.getDoorStatus());
        status.put("Window", window.getWindowStatus());
        status.put("Temperature", String.valueOf(temperatureSensor.getCurrentTemperature()));
        status.put("Motion", String.valueOf(motionSensor.isMotionDetected()));
        return status;
    }

    private Map<String, String> response(String device, String status) {
        Map<String, String> res = new HashMap<>();
        res.put(device, status);
        return res;
    }
}