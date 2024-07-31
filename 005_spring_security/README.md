introducción a spring security

Agregaremos Spring Web y Spring Security a nuestro proyecto en intelliJ IDEA.
Para ejecutar nuestro HelloController con postman, añadiremos una nueva petición y agregamos la url a nuestro proyecto: /hello, y en GET: http://localhost:8080/hello. Pero si no configuramos nuestro archivo application.properties nos lanzará un 401 Unauthorized, ya que no tendrá el password generado en la consola de Spring, que será parecido a esto:Using generated security password: 7a4ca451-def0-433c-a046-918c1c3321ce.
Deberemos de ir al apartado de Authorization en postman y en Auth Type seleccionar un tipo, por ejemplo Basic Auth, y copiamos y pegamos el password generado por spring, y en Username "user".
Observamos que si borramos las cookies de postman no nos dejará acceder, de modo que tendremos cierta seguridad.
Para customizar la contraseña y usuario para no tener que ajustar la contraseña cada vez que se arranque la app hay que hacerlo en application.properties, evidentemente con una contraseña segura. También abría que quitar la cookie generada con anterioridad con la anterior contraseña. Habría que volver a ejecutar la app para que obtenga la nueva contraseña desde el archivo application.properties. Esta es la opcion más básica para obtener seguridad.
Para una configuración más avanzada, podemos sobreescribir SecurityConfig, la cual se anotaría con @Configuration.
En resumen:
* Dependencia: spfing-boot-starter-security * Seguridad por défecto * Cambiar usuario y contrasefia: * spring. secumity.user.name=postman * spring. secumity.user.password=postman
  la seguridacpodemos implementar las siguientes interfaces o crear @Bean:
* UserDetailsS
* @Override busqueda
  vice loadUserByUsername(String username): se puede hacer una base dle datos con Spring Data JPA y devolver un usuario real
  » UserDetails
* @Overrid getAuthorities(), getPassword(), getUsername() * AuthenticationProvider authenticate()
  » PasswordEncoder: para cifrar la dontrasefia antes de guardarla en base de datos * Como personalizer la seguridad » Basic Authentic » Usuarios en me
  « Usuarios en bas