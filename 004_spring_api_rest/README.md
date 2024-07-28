# Tu primera API Rest con Spring Boot
___
Este proyecto muestra cómo crear una API REST usando Spring Boot.

## Tecnologías y Anotaciones Utilizadas

- **Entidades y Repositorios**: `@Entity`, `@Repository`
- **Servicios**: `@Service`
- **Controladores REST**: `@RestController`
- **Métodos HTTP**: `GET`, `POST`, `PUT`, `DELETE`

## Estructura del Proyecto

El proyecto sigue una estructura orientada a capas:
1. **Model**: Contiene las entidades del proyecto.
2. **Repository**: Contiene los repositorios que gestionan la persistencia.
3. **Service**: Contiene la lógica de negocio.
4. **Controller**: Contiene los controladores que manejan las solicitudes HTTP.

## Dependencias

Usaremos el IDE IntelliJ IDEA con Maven y las siguientes dependencias:
- **Spring Web**
- **H2 Database**
- **Spring Data JPA**
- **Spring Boot DevTools**

## Primeros Pasos

### 1. Crear una Entidad

Crea la carpeta `model` y dentro de ella la entidad `Manufacturer`:

```java
package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "num_employees")
    private int numEmployees;

    @Column(name = "year")
    private int year;

    // Constructor vacío
    public Manufacturer() {}

    // Constructor con parámetros
    public Manufacturer(String name, int numEmployees, int year) {
        this.name = name;
        this.numEmployees = numEmployees;
        this.year = year;
    }

    // Getters y Setters
    // ...
}
```

### 2. Crear el Repositorio

Crea la carpeta `repository` y dentro de ella `ManufacturerRepository`:

```java
package com.example.repository;

import com.example.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    // Métodos personalizados si es necesario
}
```

### 3. Crear el Servicio

Crea la carpeta `service` y dentro de ella `ManufacturerService`:

```java
package com.example.service;

import com.example.model.Manufacturer;
import com.example.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public void deleteById(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
```

### 4. Crear el Controlador

Crea la carpeta `controller` y dentro de ella `ManufacturerController`:

```java
package com.example.controller;

import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public List<Manufacturer> findAll() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Manufacturer> findById(@PathVariable Long id) {
        return manufacturerService.findById(id);
    }

    @PostMapping
    public Manufacturer create(@RequestBody Manufacturer manufacturer) {
        return manufacturerService.save(manufacturer);
    }

    @PutMapping("/{id}")
    public Manufacturer update(@PathVariable Long id, @RequestBody Manufacturer manufacturer) {
        manufacturer.setId(id);
        return manufacturerService.save(manufacturer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        manufacturerService.deleteById(id);
    }
}
```

## Configuración

No es necesario configurar `application.properties` para H2, ya que se autoconfigura según el `pom.xml`. Sin embargo, para mejorar los logs, agrega lo siguiente en `application.properties`:

```properties
spring.main.banner-mode=off
logging.pattern.console=%clr(%logger{39}){cyan} - %msg%n
```

## Uso de Postman

Para probar la API, usa [Postman](https://www.postman.com/):

1. **GET** - Obtener todos los fabricantes:
    - URL: `http://localhost:8080/api/manufacturers`
2. **GET** - Obtener un fabricante por ID:
    - URL: `http://localhost:8080/api/manufacturers/{id}`
3. **POST** - Crear un nuevo fabricante:
    - URL: `http://localhost:8080/api/manufacturers`
    - Body (raw JSON):
    ```json
    {
        "name": "manufacturer2",
        "numEmployees": 1000,
        "year": 1990
    }
    ```
4. **PUT** - Actualizar un fabricante existente:
    - URL: `http://localhost:8080/api/manufacturers/{id}`
    - Body (raw JSON):
    ```json
    {
        "name": "updatedName",
        "numEmployees": 1500,
        "year": 2000
    }
    ```
5. **DELETE** - Eliminar un fabricante por ID:
    - URL: `http://localhost:8080/api/manufacturers/{id}`

## Manejo de Errores

Para manejar errores y excepciones, puedes agregar controladores de excepción personalizados en tu controlador para capturar y manejar fallos.

## Resumen

Crear una API REST involucra todas las operaciones CRUD (Crear, Leer, Modificar, Eliminar) y el uso adecuado de anotaciones y configuraciones para garantizar un funcionamiento correcto y eficiente.

¡Ahora estás listo para crear y manejar una API REST con Spring Boot!
