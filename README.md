# Red Domótica con WiFi en Cisco Packet Tracer

Proyecto académico para el curso **Redes Inalámbricas (54496)** — Primer Bloque, Grupo 26V04
Corporación Unificada Nacional de Educación Superior (CUN)

**Autor:** Arlez Camilo Ceron Herrera
**Docente:** Ricardo Alfredo López Bulla

---

## Descripción

Implementación de una red domótica simulada en Cisco Packet Tracer, conectada mediante una
red inalámbrica WiFi, que integra dispositivos inteligentes (puerta, ventana, lámpara y aire
acondicionado, entre otros) administrables desde un dispositivo móvil. El proyecto incluye
automatización mediante reglas condicionales configuradas en el simulador, y un backend en
**Java (Spring Boot)** que replica la misma lógica de negocio, documentado con Swagger/OpenAPI.

## Cumplimiento del requerimiento

> Implementar en Packet Tracer una red domótica usando redes WiFi, instalar ventanas, puertas,
> lámparas, aire acondicionado, entre otros, administrados desde un celular o cualquier
> dispositivo inalámbrico. Debe incluir programación en Python o Java y automatización. Entregar
> informe técnico en PDF y .pkt funcional.

| Requisito | Cómo se cumplió |
|---|---|
| Red WiFi con dispositivos domóticos | Topología en `pkt/` con Home Gateway + 6 dispositivos IoT conectados por WiFi |
| Administración desde celular | Smartphone conectado a la red, con acceso al panel web del Home Gateway |
| Programación (Java) | Backend en `java/domotica-java/` (Spring Boot, documentado con Swagger) |
| Automatización | Reglas *Conditions* configuradas en el Home Gateway (temperatura → A/C, movimiento → luz) |
| Informe técnico en PDF | `informe/informe-tecnico-red-domotica.pdf` (formato APA) |
| .pkt funcional | `pkt/red-domotica.pkt` |

## Estructura del repositorio

```
red-domotica-packet-tracer/
  |-- README.md
  |-- pkt/
  |     \-- red-domotica.pkt              # Topologia funcional de Packet Tracer
  |-- java/
  |     |-- domotica-java/                # Backend Spring Boot
  |     \-- red-domotica-postman-collection.json
  |-- informe/
  |     \-- informe-tecnico-red-domotica.pdf
  \-- docs/
        \-- historias-usuario.md
```

## Topología de red

- **Home Gateway** (SSID `CasaInteligente`, WPA2-PSK/AES): controlador central de la red WiFi y servidor IoT.
- **Smartphone**: dispositivo móvil de administración remota.
- **Dispositivos IoT**: Puerta, Ventana, Lámpara, Ceiling Fan (A/C), Sensor de Movimiento, Sensor de Temperatura.

## Historias de usuario

Ver detalle completo en [`docs/historias-usuario.md`](docs/historias-usuario.md).

| ID | Resumen |
|---|---|
| HU1 | Control remoto de la lámpara desde el celular |
| HU2 | Encendido automático del A/C por temperatura > 28°C |
| HU3 | Monitoreo del estado de la puerta |
| HU4 | Encendido automático de la lámpara por movimiento |
| HU5 | Control remoto de la ventana |
| HU6 | Modo "Away" (apagado general para ahorro de energía) |

## Automatización

Configurada mediante *Conditions* en el Home Gateway (`http://192.168.25.1/conditions.html`):

- **HU2:** SI `IoT7 (Temperature) > 28.0°C` ENTONCES `IoT3 (Ceiling Fan) Status = High`.
- **HU4:** SI `IoT6 (Motion Detector) On = true` ENTONCES `IoT2 (Light) Status = On`.

## Instalación del entorno Java

Pasos para preparar el entorno en macOS antes de ejecutar el backend.

### 1. Instalar Homebrew (si no lo tienes)

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### 2. Instalar Java 17 (OpenJDK)

```bash
brew install openjdk@17
```

Si `java` no queda enlazado automáticamente al PATH, agrégalo manualmente:

```bash
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### 3. Instalar Maven

```bash
brew install maven
```

### 4. Verificar la instalación

```bash
java -version
mvn -version
```

Salida esperada (las versiones pueden variar ligeramente):

```
openjdk version "17.0.16" ...
Apache Maven 3.9.11 ...
Java version: 17.0.16 ...
```

### 5. (Opcional) IDE — IntelliJ IDEA

El proyecto también puede abrirse directamente con **IntelliJ IDEA** (Community o Ultimate):

1. `File → Open...` y selecciona la carpeta `java/domotica-java` (donde está el `pom.xml`).
2. Espera a que IntelliJ indexe las dependencias de Maven automáticamente.
3. Verifica en `File → Project Structure` que el Project SDK sea Java 17.
4. Ejecuta la clase `DomoticaApplication` (flecha ▶ verde) para iniciar el servidor.

## Backend en Java (Spring Boot)

Réplica en Java de la misma lógica de automatización, ya que Packet Tracer no permite ejecutar
Java de forma nativa dentro del simulador.

### Requisitos
- Java 17+
- Maven 3.9+

### Ejecutar

```bash
cd java/domotica-java
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`, y la documentación interactiva (Swagger UI) en:

```
http://localhost:8080/swagger-ui/index.html
```

### Endpoints principales

| Método | Endpoint | Historia de usuario |
|---|---|---|
| GET | `/api/devices/status` | Estado general |
| POST | `/api/devices/light/{on\|off}` | HU1 |
| POST | `/api/devices/temperature/{value}` | HU2 |
| GET / POST | `/api/devices/door` | HU3 |
| POST | `/api/devices/motion/{true\|false}` | HU4 |
| POST | `/api/devices/window/{open\|close}` | HU5 |
| POST | `/api/devices/away-mode` | HU6 |

Colección de Postman lista para importar: [`java/red-domotica-postman-collection.json`](java/red-domotica-postman-collection.json).

## Informe técnico

El documento completo (formato APA, con tabla de contenido, evidencia de pruebas y capturas)
está disponible en [`informe/informe-tecnico-red-domotica.pdf`](informe/informe-tecnico-red-domotica.pdf).

## Evidencia de funcionamiento

La automatización fue validada en modo *Realtime* de Packet Tracer: al forzar la temperatura
ambiente por encima de 28°C, el Ceiling Fan (A/C) se encendió automáticamente, confirmando el
correcto funcionamiento de la regla sensor → condición → actuador. La misma lógica fue verificada
exitosamente en el backend Java mediante Postman.