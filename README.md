# Spring Cloud Gateway - Licencia Service Configuration

Este proyecto implementa un **Spring Cloud Gateway** para enrutar solicitudes hacia el servicio de licencias (`licencia-service`). Se configuran cabeceras personalizadas que se agregan a las respuestas del servidor y se aplica un filtro global para agregar o modificar ciertas cabeceras en todas las solicitudes que pasan a través del Gateway.

## Requisitos

- Java 21 o superior
- Spring Boot 3.3.2
- Maven 3.x o superior
- Configuración de `Eureka` para el servicio de descubrimiento

## Configuración

El Gateway se ha configurado para agregar las siguientes cabeceras personalizadas en las respuestas:

- `Fecha`: Fecha y hora actual del servidor en formato `yyyy-MM-dd HH:mm:ss` (Se agregó por medio de código)
- `Usuario`: Un valor fijo que representa al usuario (`UserMitocode`) (Se agregó por medio de código)
- `X-Tracking-ID`: Un identificador de seguimiento con valor fijo (`tracking-value-xyz`) (Se agregó por medio de configuración)

Además, se ha implementado un **filtro global** que:

- Agrega o modifica la cabecera `appCallerName`, estableciendo el valor `CloudGateway` si no está presente en la solicitud.

### Archivo `application.yml`

```yaml
server:
  port: 9020

# Spring Cloud Gateway Router
spring:
  cloud:
    gateway:
      routes:
        - id: licencia-service-route
          uri: lb://licencia-service
          predicates:
            - Path=/api/licencia-service/**
          filters:
            - StripPrefix=2
            - LicenciaServiceFilter=X-Tracking-ID,tracking-value-xyz
```

Ejemplo de respuesta: 
```http request
GET http://localhost:9020/api/licencia-service/gestor-licencias-api/titulares/getAllTitulares

HTTP/1.1 200 OK
transfer-encoding: chunked
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Date: Fri, 27 Sep 2024 01:11:54 GMT
##-----Agregados
Fecha: 2024-09-26 20:11:54
Usuario: UserMitocode
X-Tracking-ID: tracking-value-xyz

[
  {
    "id": 21,
    "numeroDocumento": "12345679",
    "nombres": "LUIS",
    "apellidos": "CARRASCO",
    "fechaNacimiento": "1985-06-25",
    "direccion": "Calle Falsa 123"
  },
  {
    "id": 1,
    "numeroDocumento": "12345678",
    "nombres": "Juan",
    "apellidos": "Pérez Lópeggggghhhhh",
    "fechaNacimiento": "1985-06-25",
    "direccion": "Calle Falsa 123"
  }
]
```