# Introducción a Spring Security
___

Este proyecto demuestra cómo integrar y configurar Spring Security en una aplicación Spring Boot.

## Dependencias

Para agregar seguridad a nuestro proyecto, utilizamos las siguientes dependencias en `pom.xml`:

- **Spring Web**
- **Spring Security**

## Configuración Básica de Seguridad

1. **Configuración Predeterminada:**
   Spring Security proporciona una configuración predeterminada que protege todas las rutas de la aplicación. Al ejecutar la aplicación, se genera automáticamente un usuario y una contraseña que se muestran en la consola, por ejemplo:


   ``` Using generated security password: 7a4ca451-def0-433c-a046-918c1c3321ce```

2. **Acceso a Endpoints Protegidos:**
   Para acceder a los endpoints, como por ejemplo `/hello`, desde herramientas como Postman, se debe incluir el usuario y la contraseña generados. 

   - **URL de ejemplo:** `http://localhost:8080/hello`
   - **Método:** GET
   - **Autenticación en Postman:**
     - **Auth Type:** Basic Auth
     - **Username:** `user`
     - **Password:** [Password generado]

3. **Manejo de Cookies:**
   Si se eliminan las cookies en Postman, se requiere volver a autenticarse, lo que proporciona una capa adicional de seguridad.

## Personalización de Usuario y Contraseña

Para evitar tener que utilizar la contraseña generada automáticamente cada vez que se arranca la aplicación, se puede definir un usuario y contraseña personalizados en el archivo `application.properties`:

```properties
spring.security.user.name=postman
spring.security.user.password=postman
```

**Nota:** Es importante usar contraseñas seguras y gestionar las cookies generadas anteriormente con contraseñas previas para evitar problemas de autenticación.

## Configuración Avanzada

Para una configuración más avanzada, puedes sobreescribir la configuración predeterminada de Spring Security creando una clase `SecurityConfig` anotada con `@Configuration`.

### Ejemplo de Configuración Avanzada

```java
package com.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password("{noop}password") // {noop} indica que no se debe cifrar la contraseña
            .roles("USER");
    }
}
```

### Componentes Personalizables

- **UserDetailsService:** Implementa la lógica para cargar detalles de usuario (como desde una base de datos).
- **PasswordEncoder:** Para cifrar las contraseñas antes de almacenarlas.
- **AuthenticationProvider:** Para personalizar la autenticación.

## Resumen

Spring Security es una herramienta poderosa para agregar seguridad a las aplicaciones Spring. La configuración puede variar desde una simple autenticación básica hasta complejas configuraciones personalizadas que involucran bases de datos, cifrado de contraseñas y políticas de autorización.

## Ejecución

Para probar la configuración de seguridad:

1. Ejecuta la aplicación.
2. Usa Postman para enviar solicitudes a los endpoints protegidos.
3. Configura la autenticación básica con el usuario y la contraseña definidos.

Asegúrate de ajustar las configuraciones según las necesidades de tu proyecto y mantener siempre las credenciales seguras.
