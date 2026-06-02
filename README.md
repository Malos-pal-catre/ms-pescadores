# ms-pescadores

Microservicio encargado de la gestión completa de los pescadores artesanales registrados en la **Caleta Lo Abarca**. Forma parte del sistema de gestión de subasta artesanal desarrollado con arquitectura de microservicios Spring Boot.

## ¿Qué hace?

Administra el registro y ciclo de vida de los pescadores artesanales. Almacena su información personal, licencia de pesca, sindicato al que pertenecen y estado de actividad. Es consultado por otros microservicios como `ms-embarcaciones` y `ms-pagos` para obtener los datos del pescador asociado a una operación.

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/pescadores` | Lista todos los pescadores |
| GET | `/api/pescadores/{id}` | Busca un pescador por ID |
| GET | `/api/pescadores/rut/{rut}` | Busca un pescador por RUT |
| GET | `/api/pescadores/activos` | Lista solo los pescadores activos |
| GET | `/api/pescadores/buscar?nombre=` | Busca pescadores por nombre |
| POST | `/api/pescadores` | Registra un nuevo pescador |
| PUT | `/api/pescadores/{id}` | Actualiza los datos de un pescador |
| DELETE | `/api/pescadores/{id}` | Elimina un pescador |

## Ejemplo de uso

**Registrar pescador:**
```json
POST /api/pescadores
{
  "nombre": "Segundo",
  "apellido": "Ramírez",
  "rut": "12345678-9",
  "licencia": "LIC-001",
  "sindicato": "Sindicato Caleta Lo Abarca",
  "activo": true
}
```

**Respuesta:**
```json
{
  "id": 1,
  "nombre": "Segundo",
  "apellido": "Ramírez",
  "rut": "12345678-9",
  "licencia": "LIC-001",
  "sindicato": "Sindicato Caleta Lo Abarca",
  "activo": true
}
```

## Tecnologías

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Boot Validation
- PostgreSQL (Neon)
- Lombok

## Configuración

Crear el archivo `src/main/resources/application.properties` con:

```properties
spring.application.name=ms-pescadores
server.port=8081

spring.datasource.url=jdbc:postgresql://<HOST>/pescadores_db?sslmode=require&channelBinding=require
spring.datasource.username=<USUARIO>
spring.datasource.password=<PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## Cómo correr

```bash
mvnw.cmd spring-boot:run
```

El servicio queda disponible en `http://localhost:8081`

## Estructura del proyecto

```
ms-pescadores/
├── controller/    → PescadorController (endpoints REST)
├── service/       → PescadorService (lógica de negocio)
├── repository/    → PescadorRepository (JPA + @Query)
├── model/         → Pescador (entidad JPA)
├── dto/           → RequestDTO, ResponseDTO, Mapper
└── exception/     → GlobalExceptionHandler, RecursoNoEncontradoException
```

## Parte del sistema

Este microservicio es parte del sistema **Caleta Lo Abarca** junto a:
`ms-embarcaciones` · `ms-especies` · `ms-capturas` · `ms-auth` · `ms-subastas` · `ms-compradores` · `ms-pagos` · `ms-bodega` · `ms-vedas` · `ms-reportes`
