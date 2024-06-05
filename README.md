# English version

## Local Hotel Accommodation Management Project

REST API development for hotels management

### Description

This application is developed in Java version 22, using Spring Boot version 3.3.0 and managed with Maven. Its purpose is to manage local hotels, allowing the creation, modification, and deletion of hotels and rooms, as well as the management of reservations.

The application follows a domain-driven architecture (hexagonal pattern) and employs various software design patterns such as Dependency Injection, Configuration, Facade, Builder, Singleton, Template Method, and Strategy. It also applies principles of OOP, functional programming, and SOLID, KISS, DRY, YAGNI, and clean code principles.

### Prerequisites

- Java 22
- Maven
- MySQL
- Postman (optional, for API testing)

### Database Configuration

The application connects to a MySQL database. To configure it, update the properties `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in the `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
```

### Project Structure

The repository is divided into the following folders:

1. **Code - SpringBoot**: Contains all the application source code.
2. **Collections - Postman**: Includes the Postman collection configured to test the endpoints. Simply import and use.
3. **Database - MySQL**: Contains the database script to import and use.

### Running the Application

The application will run locally on port 8082. To start it, use the following command in the project root:

```bash
mvn spring-boot:run
```

### API Documentation

The OpenAPI documentation and specification are available at the following resources and do not require authentication:

- [Swagger UI](http://localhost:8082/v1/swagger-ui/)
- [OpenAPI Spec](http://localhost:8082/v1/v3/api-docs/)

### Public Endpoints

In addition to the documentation resources mentioned above, the following endpoints do not require authentication:

- `POST /api/users/register`: Register new users.
- `POST /api/auth/login`: User login.

The rest of the endpoints require authentication.

### API Testing

To test the API endpoints, import the Postman collection available in the `Collections - Postman` folder. This collection is pre-configured with all the application's endpoints.

### Design Patterns and Principles

The application is developed following the hexagonal architecture and using various design patterns and software development principles:

- **Dependency Injection**: Improves modularity and facilitates testing by injecting dependencies.
- **Configuration**: Centralized configuration management.
- **Facade**: Simplifies interaction with complex subsystems.
- **Builder**: Constructs complex objects in a controlled manner.
- **Singleton**: Ensures a single instance of certain classes.
- **Template Method**: Defines the structure of an algorithm, allowing steps to be redefined.
- **Strategy**: Selects algorithms at runtime.
- **OOP (Object-Oriented Programming)**: Encapsulation, inheritance, and polymorphism.
- **Functional Programming**: Uses functions as first-class citizens.
- **SOLID**: Design principles to create flexible and maintainable software.
- **KISS**: Keep it simple and straightforward.
- **DRY**: Don't repeat yourself.
- **YAGNI**: You aren't gonna need it.
- **Clean Code**: Readable and maintainable code.

### Contributions

Contributions are welcome. To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push your branch (`git push origin feature/new-feature`).
5. Open a Pull Request.

### License

This project is licensed under the terms of the [MIT License](LICENSE).

---

This README provides a comprehensive guide to setting up, running, and contributing to the project. If you have any questions or encounter any issues, please feel free to open an issue in the repository.




# Versión en Español

## Proyecto de administración de alojamiento de hoteles

Desarrollo de API REST para la gestión de hoteles

### Descripción

Esta aplicación está desarrollada en Java versión 22, utilizando Spring Boot versión 3.3.0 y es administrada con Maven. Su propósito es gestionar hoteles locales, permitiendo la creación, modificación y eliminación de hoteles y habitaciones, así como la administración de reservas. 

La aplicación sigue una arquitectura orientada al dominio (patrón hexagonal) y emplea varios patrones de diseño de software como Dependency Injection, Configuration, Facade, Builder, Singleton, Template Method y Strategy. Además, aplica principios de POO, programación funcional y principios SOLID, KISS, DRY, YAGNI y clean code.

### Requisitos Previos

- Java 22
- Maven
- MySQL
- Postman (opcional, para pruebas de API)

### Configuración de la Base de Datos

La aplicación se conecta a una base de datos MySQL. Para configurarla, actualiza las propiedades `spring.datasource.url`, `spring.datasource.username` y `spring.datasource.password` en el archivo `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
```

### Estructura del Proyecto

El repositorio se divide en las siguientes carpetas:

1. **Code - SpringBoot**: Contiene todo el código fuente de la aplicación.
2. **Collections - Postman**: Incluye la colección de Postman configurada para probar los endpoints. Simplemente impórtala en Postman y úsala.
3. **Database - MySQL**: Contiene el script de la base de datos para importar y usar.

### Ejecución de la Aplicación

La aplicación se ejecutará localmente en el puerto 8082. Para iniciarla, utiliza el siguiente comando en la raíz del proyecto:

```bash
mvn spring-boot:run
```

### Documentación de la API

La documentación y especificación OpenAPI se encuentran disponibles en los siguientes recursos y no requieren autenticación:

- [Swagger UI](http://localhost:8082/v1/swagger-ui/)
- [OpenAPI Spec](http://localhost:8082/v1/v3/api-docs/)

### Endpoints Públicos

Además de los recursos de documentación mencionados anteriormente, los siguientes endpoints no requieren autenticación:

- `POST /api/users/register`: Registro de nuevos usuarios.
- `POST /api/auth/login`: Inicio de sesión.

El resto de los endpoints requieren autenticación.

### Pruebas de API

Para probar los endpoints de la API, importa la colección de Postman disponible en la carpeta `Collections - Postman`. Esta colección está preconfigurada con todos los endpoints de la aplicación.

### Patrones de Diseño y Principios

La aplicación está desarrollada siguiendo la arquitectura hexagonal y utilizando varios patrones de diseño y principios de desarrollo de software:

- **Dependency Injection**: Inyección de dependencias para mejorar la modularidad y facilitar las pruebas.
- **Configuration**: Configuración centralizada para facilitar la gestión de propiedades.
- **Facade**: Simplificación de la interacción con subsistemas complejos.
- **Builder**: Construcción de objetos complejos de manera controlada.
- **Singleton**: Garantía de una única instancia de ciertas clases.
- **Template Method**: Definición de la estructura de un algoritmo con la posibilidad de redefinir ciertos pasos.
- **Strategy**: Selección de algoritmos en tiempo de ejecución.
- **POO (Programación Orientada a Objetos)**: Encapsulación, herencia y polimorfismo.
- **Programación Funcional**: Uso de funciones como ciudadanos de primera clase.
- **SOLID**: Principios de diseño para crear software flexible y mantenible.
- **KISS**: Mantener las cosas simples y directas.
- **DRY**: No repetir el código.
- **YAGNI**: No construir funcionalidades innecesarias.
- **Clean Code**: Código legible y mantenible.

### Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva característica'`).
4. Empuja tu rama (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

### Licencia

Este proyecto está licenciado bajo los términos de la [MIT License](LICENSE).

---

Este README proporciona una guía completa para configurar, ejecutar y contribuir al proyecto. Si tienes alguna pregunta o encuentras algún problema, no dudes en abrir un issue en el repositorio.