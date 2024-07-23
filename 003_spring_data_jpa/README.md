## Al crear el proyecto:
Usaremos como dependencias:
Developer Tools -> Spring Boot DevTools
SQL -> MySQLDriver, y para trabajar a alto nivel con ORM -> Spring Data JPA.

## Primer paso
### Comprobar las dependencias del POM en https://mvnrepository.com/
## Segundo paso
### Configuración de la base de datos en application.properties.
Necesitamos asegurarnos de que MySQL esté escuchando en el puerto 3306, de modo que al instalar debe de estar configuradop en ese puerto, y si es otro que coincida con esta línea del archivo properties:
spring.datasource.url=jdbc:mysql://localhost:3306/spring_data_jpa
Debemos de instalar el MySQL Community Server.
Debe de estar iniciado para ejecutar la aplicación de Spring Data.
Nos podemos conectar con MySQL WorkBench por ejemplo, o DBeaver Community.
También desde IntelliJ Idea Ultimate, desde Data Source > MySQL también podemos conectarnos.
En MySQL Workbench crearíamos la base de datos con el mismo nombre que hemos puesto en el archivo properties (spring_data_jpa en este caso concreto).
En IntelliJ vamos a MySQL desde Data Sources and Drivers, y configuramos MySQL.
## Configuración de la Base de Datos en MySQL

Para configurar la conexión a una base de datos MySQL, es necesario actualizar el archivo `application.properties` con las credenciales y el driver adecuados. A continuación se muestra un ejemplo de configuración:

```properties
# Configuración del datasource
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
````

Es importante asegurarse de que las credenciales (nombre de usuario y contraseña) sean las mismas que se utilizaron al instalar MySQL.
Establecemos spring.jpa.show-sql=true en desarrollo nunca en producción, para poder ver las consultas.
spring.jpa.hibernate.ddl-auto=create también es solo en desarrollo para que se cree automáticamente la BBDD, sino usaríamos update o none...
Para especificar concretamente la BBDD usamos MySQLDialect, que encontraremos en las librerías externas de Maven:org.hibernate.orm:hibernate-core, en su carpeta dialect. Puede variar la versión.
Para poder acceder a la BBDD externa de MySQL debemos de indicarlo con: spring.sql.init.mode=always.
Y con spring.jpa.defer-datasource-initialization=true evitamos que no le de tiempo a crear el esquema, de modo que al insertar los datos ya esté creado el esquema de la base de datos.
Conviene que cada capa tenga su propio modelo, en este caso creamos una carpeta llamada model en com.example.
Y dentro de esa carpeta creamos en nusetro caso una entidad Employee, con una clase a la que le agregamos la anotación @Entity, y la anotación @Table para poder usar la tabla en SQL, para que el framework de persistencia luego lo gestione todo.
Llamamos a la tabla "employee", todo en minúsculas o todo mayúsculas, para que no arroje errores con la base de datos.
Para agregar la clave primaria a esta tabla usamos la anotación @Id al atributo de la clase (id).
Con @GeneratedValue(strategy = GenerationType.IDENTITY) conseguimos que cada tabla genere sus propios valores.
Algo similar hacemos con @Column para el campo o atributo fullName.
Aconsejable eel constructor vacío, y además agregar getters y setters, y un constructor con todos los parámetros.
Así se podrá generar la información que llegue de base de datos en objetos, haciéndolo el propio framework.
No nos olvidamos tampoco de el método toString.
Ahora bien, en la carpeta repository tendremos una interface que extenderá a JpaRepository.
La anotación @Repository de la interface no es obligatoria, ya que al extender de JpaRepository ya lo detecta en framework.
spring jpa se construye así: DB -> JDBC -> Hibernate -> Spring Data JPA, de modo que son varias capas sobrepuestas, de modo que con spring Data JPA podemos hacer muchas cosas con poco esfuerzo.
Crear las interfaces de Repository si queremos hacer operaciones sí que es necesario.
Gracias a la anotación @Repository de las interfaces de repository podemos manejar la base de datos desde spring, mediante el ApplicationContext.getBean, puediendo por ejemplo obtener todos los registros de una tabla con findAll(). Y si quisiéramos personalizar la manera de manejar la base de datos crearemos métodos en las interfaces del repository.
Cuando usamos Optional<> debemos de estar seguros que el resultado sea solo uno, una propiedad que sea única, si no dará error. También se puede unir dos condiciones con And o usar Or. Si es más de un resultado usaremos un List<> y la condición...
Resumiendo, siempre crear las entidades y customizar los repositorios.
Gracias a spring nos abstraemos de todo lo que hay por debajo y no necesitamos usar directamente Hibernate, sino que lo hacemos desde spring.
Verificación de las Tablas en MySQL
Para verificar si las tablas se han creado correctamente, se puede utilizar MySQL Workbench. Al conectar a la base de datos desde MySQL Workbench, deberíamos poder ver las tablas creadas por Spring Data JPA.

En este momento, si no se han creado tablas, es porque el esquema aún no se ha generado. A medida que continuamos configurando Spring Data JPA, las tablas se crearán automáticamente según las entidades definidas en el proyecto.
