# Spring Data JPA Project

Este proyecto muestra cómo configurar y utilizar Spring Data JPA con MySQL.

## Dependencias

Para este proyecto, usaremos las siguientes dependencias:

- **Developer Tools**: Spring Boot DevTools
- **SQL**: MySQL Driver
- **ORM**: Spring Data JPA

## Primeros Pasos

### 1. Comprobar las dependencias del POM

Asegúrate de que las dependencias necesarias estén incluidas en el archivo `pom.xml`. Puedes buscar y añadir las dependencias desde [Maven Repository](https://mvnrepository.com/).

### 2. Configuración de la Base de Datos

#### Configuración en `application.properties`

Asegúrate de que MySQL esté escuchando en el puerto 3306 (o el puerto configurado en tu instalación). Configura la conexión a la base de datos en el archivo `application.properties`:

```properties
# Configuración del datasource
spring.datasource.url=jdbc:mysql://localhost:3306/spring_data_jpa
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de JPA
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

**Notas:**
- Asegúrate de que las credenciales (`username` y `password`) sean correctas.
- `spring.jpa.show-sql=true` es útil en desarrollo para ver las consultas SQL generadas, pero no debe usarse en producción.
- `spring.jpa.hibernate.ddl-auto=create` crea automáticamente la base de datos; en producción, usa `update` o `none`.
- `spring.sql.init.mode=always` asegura que los scripts SQL se ejecuten siempre al iniciar la aplicación.
- `spring.jpa.defer-datasource-initialization=true` garantiza que el esquema de la base de datos se cree antes de insertar datos.

### 3. Crear la Base de Datos

Puedes crear la base de datos usando MySQL Workbench, DBeaver o IntelliJ IDEA:

```sql
CREATE DATABASE spring_data_jpa;
```

Configura la conexión a la base de datos en IntelliJ IDEA desde `Data Sources and Drivers`.

## Crear Entidades y Repositorios

### Crear la Entidad `Employee`

En la carpeta `model`, crea la clase `Employee`:

```java
package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    // Constructor vacío
    public Employee() {}

    // Constructor con parámetros
    public Employee(String fullName) {
        this.fullName = fullName;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
```

### Crear el Repositorio `EmployeeRepository`

En la carpeta `repository`, crea una interfaz que extienda `JpaRepository`:

```java
package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Puedes agregar métodos personalizados aquí
}
```

### Verificación de las Tablas en MySQL

Para verificar que las tablas se han creado correctamente, usa MySQL Workbench:

1. Conéctate a la base de datos.
2. Verifica que la tabla `employee` haya sido creada.

Si las tablas no se han creado, asegúrate de que el esquema de la base de datos esté configurado correctamente y que las entidades estén definidas en el proyecto.

## Resumen

- Asegúrate de que MySQL esté correctamente configurado y en funcionamiento.
- Configura `application.properties` con las credenciales y ajustes necesarios.
- Crea las entidades y los repositorios necesarios para interactuar con la base de datos.
- Verifica que las tablas se hayan creado correctamente usando MySQL Workbench o una herramienta similar.

¡Ahora estás listo para trabajar con Spring Data JPA y MySQL en tu proyecto!
```
