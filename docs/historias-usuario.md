# Historias de Usuario — Red Domótica

Este documento recoge las historias de usuario que guían la implementación de la red domótica en Cisco Packet Tracer. Cada historia está vinculada a un Issue en el repositorio de GitHub.

---

## HU1 - Control remoto de lámpara desde celular

**Historia de usuario**
Como usuario, quiero encender o apagar la lámpara desde mi celular, para controlar la iluminación de mi casa remotamente sin necesidad de estar presente.

**Criterios de aceptación**
- [ ] La lámpara está conectada a la red WiFi del Home Gateway.
- [ ] El dispositivo aparece registrado en el panel de control (Things).
- [ ] Desde el smartphone conectado a la misma red, es posible encender/apagar la lámpara.
- [ ] El cambio de estado se refleja correctamente en la simulación (modo Simulation).

**Componentes involucrados**
- Lámpara (Light)
- Home Gateway
- Smartphone

**Prioridad:** Alta
**Labels:** `lampara`, `control-remoto`

---

## HU2 - Encendido automático del aire acondicionado por temperatura

**Historia de usuario**
Como usuario, quiero que el aire acondicionado se encienda automáticamente cuando la temperatura supere los 28°C, para mantener el confort del hogar sin intervención manual.

**Criterios de aceptación**
- [ ] Existe un sensor de temperatura conectado a la red WiFi.
- [ ] Se configura una regla (Condition) en el Home Gateway: si temperatura > 28°C, encender A/C.
- [ ] Al simular un aumento de temperatura, el A/C se enciende automáticamente.
- [ ] Al bajar la temperatura por debajo del umbral, el A/C se apaga.

**Componentes involucrados**
- Sensor de temperatura
- Aire acondicionado (HVAC)
- Home Gateway

**Prioridad:** Alta
**Labels:** `ac`, `automatizacion`

---

## HU3 - Monitoreo del estado de la puerta principal

**Historia de usuario**
Como usuario, quiero ver el estado de la puerta principal (abierta/cerrada) desde el panel del celular, para verificar la seguridad de mi hogar en cualquier momento.

**Criterios de aceptación**
- [ ] La puerta inteligente está registrada en el Home Gateway.
- [ ] El estado (abierta/cerrada) se muestra en tiempo real en el panel accedido desde el smartphone.
- [ ] Al abrir/cerrar la puerta en simulación, el estado se actualiza correctamente en el panel.

**Componentes involucrados**
- Puerta inteligente (Smart Door)
- Home Gateway
- Smartphone

**Prioridad:** Media
**Labels:** `puerta`, `seguridad`

---

## HU4 - Encendido de lámpara por detección de movimiento

**Historia de usuario**
Como usuario, quiero que la lámpara se encienda automáticamente si un sensor detecta movimiento, para mejorar la seguridad durante la noche.

**Criterios de aceptación**
- [ ] Existe un sensor de movimiento conectado a la red.
- [ ] Se configura una regla: si sensor de movimiento = detectado, encender lámpara.
- [ ] Al simular detección de movimiento, la lámpara se enciende automáticamente.

**Componentes involucrados**
- Sensor de movimiento
- Lámpara
- Home Gateway

**Prioridad:** Media
**Labels:** `lampara`, `seguridad`, `automatizacion`

---

## HU5 - Control remoto de ventana desde dispositivo móvil

**Historia de usuario**
Como usuario, quiero abrir o cerrar la ventana desde mi dispositivo móvil, para controlar la ventilación del hogar sin estar físicamente en el lugar.

**Criterios de aceptación**
- [ ] La ventana inteligente está conectada a la red WiFi y registrada en el Home Gateway.
- [ ] Desde el smartphone es posible abrir/cerrar la ventana manualmente.
- [ ] El cambio de estado se refleja correctamente en la simulación.

**Componentes involucrados**
- Ventana inteligente (Window)
- Home Gateway
- Smartphone

**Prioridad:** Media
**Labels:** `ventana`, `control-remoto`

---

## HU6 - Modo "Away" para apagado general de dispositivos

**Historia de usuario**
Como usuario, quiero que el sistema apague todos los dispositivos con un solo control, para ahorrar energía cuando salgo de casa (modo "Away").

**Criterios de aceptación**
- [ ] Existe un mecanismo (regla o control manual agrupado) que apaga lámpara y A/C simultáneamente.
- [ ] El modo "Away" se puede activar desde el panel del smartphone.
- [ ] Al activarlo, todos los dispositivos configurados cambian a estado apagado.

**Componentes involucrados**
- Lámpara
- Aire acondicionado
- Home Gateway
- Smartphone

**Prioridad:** Baja
**Labels:** `automatizacion`, `eficiencia-energetica`

---

## Resumen de trazabilidad

| ID  | Historia                                   | Prioridad | Issue en GitHub |
|-----|---------------------------------------------|-----------|------------------|
| HU1 | Control remoto de lámpara                   | Alta      | #1               |
| HU2 | A/C automático por temperatura              | Alta      | #2               |
| HU3 | Monitoreo estado de puerta                  | Media     | #3               |
| HU4 | Lámpara automática por movimiento           | Media     | #4               |
| HU5 | Control remoto de ventana                   | Media     | #5               |
| HU6 | Modo Away (apagado general)                 | Baja      | #6               |

*Nota: actualizar el número de Issue (#) en la tabla una vez creados en GitHub, según el orden real asignado.*