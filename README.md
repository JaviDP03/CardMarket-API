# CardMarket-API

Repositorio de la API (backend) del proyecto final de 2º DAW

## 🚀 Tecnologías principales

[![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.9-C71A36?logo=apache-maven)](https://maven.apache.org/)
[![MariaDB](https://img.shields.io/badge/MariaDB-11.7.2-003545?logo=mariadb)](https://mariadb.org/)
[![JWT](https://img.shields.io/badge/JWT-io.jsonwebtoken-000000?logo=jsonwebtokens)](https://github.com/jwtk/jjwt)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-3.1-6BA539?logo=openapi-initiative)](https://swagger.io/specification/)

## 📦 Dependencias principales

- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Data JPA
- Spring Boot Starter Tomcat (provided)
- MariaDB Java Client
- Lombok
- JJWT (JSON Web Token)
- Springdoc OpenAPI
- Hibernate Validator

## ⚙️ Configuración y despliegue

### 1. Clona el repositorio

```bash
git clone https://github.com/JaviDP03/CardMarket-API.git
cd CardMarket-API
```

### 2. Configura la base de datos
Asegúrate de tener una base de datos MariaDB en funcionamiento y crea una base de datos llamada `cardmarket`.

```sql
CREATE DATABASE cardmarket;
```

### 3. Configura el archivo `application.properties`
Actualiza el archivo src/main/resources/application.properties con tus credenciales y hostname:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/cardmarket
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
```

### 4. Compila y ejecuta la aplicación

```bash
mvn clean install
mvn spring-boot:run
```
La aplicación se ejecutará por defecto en http://localhost:8080.

## 📖 Documentación
La documentación de la API está disponible en http://localhost:8080/swagger-ui/index.html.
Puedes ver todos los endpoints y probarlos directamente desde la interfaz de Swagger.

## 🛡️ Seguridad
La autenticación se realiza mediante JWT. Para acceder a los endpoints protegidos:
1. Obtén un token usando el endpoint de login.
2. Incluye el token en la cabecera Authorization como Bearer <token> en tus peticiones.