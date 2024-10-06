# Spring Cloud Gateway - Licencia Service Configuration

Este proyecto implementa un **Spring Cloud Gateway** para enrutar solicitudes hacia el servicio de licencias (`licencia-service`). Se configuran cabeceras personalizadas que se agregan a las respuestas del servidor y se aplica un filtro global para agregar o modificar ciertas cabeceras en todas las solicitudes que pasan a través del Gateway.


## Requisitos
- Java 21 o superior
- Spring Boot 3.3.2
- Maven 3.x o superior
- ORACLE DB 
- MONGO DB

## BD
### ORACLE:
- Se debe crear un esquema **mitocode** al crear las tablas core en oracle.
- El proyecto incluye el mantenimiento CRUD para tres tablas principales, utilizando Oracle como base de datos relacional
### MONGO
- MongoDB se utiliza para la auditoría del sistema, donde se registran los eventos y cambios realizados.
- MongoDB Para el manejo de usuarios y autenticación, se utiliza JWT (JSON Web Token), garantizando la seguridad en el acceso a los servicios.
- MongoDB se usa para implementar el patrón CQRS (Command Query Responsibility Segregation) para separar las responsabilidades de lectura y escritura, mejorando la escalabilidad del sistema.

_Nota: 
_Al utilizar JPA, no es necesario crear manualmente las tablas en la base de datos. Simplemente al levantar los servicios, Hibernate se encarga automáticamente de generar las tablas correspondientes según las entidades definidas en el proyecto.__

_Para que se inserte en auditoria se debe levantar el servicio de Kafka ya que el componente de Auditoria realiza los inserts._
## Se adjunta POSTMA
[MICROSERVICES.postman_collection.json](MICROSERVICES.postman_collection.json)

## El proyecto incluye el mantenimiento CRUD para tres tablas principales:
- Licencia: Contiene información sobre las licencias emitidas, como el número de licencia, fechas de emisión y vencimiento, y su estado.
- Titular: Relacionado con la tabla de Licencia, almacena información del titular de la licencia, como su número de documento, nombres, apellidos y dirección.
- TipoLicencia: Relacionada también con la tabla de Licencia, describe la categoría y restricciones del tipo de licencia.

## Configuración de Kafka
- La conf. se mantiene a la realizada en el curso manteniendo los puertos.