# Tu primera API Rest con Spring Boot

Vamos aver:
Entidades y repositorios: @Entity, @Repository
Servicios: @Service
Controladores REST: @RestController
Metodos HTTP: GET, POST, PUT, DELETE

Vamos a crear una api rest con spring bbot.

Usaremos el ide IntelliJ IDEA, con Maven.

Como dependencias  de Web usaremos Spring Web, y para SQL en este caso usaremos una embebida (H2 Database) y Spring Data JPA.
Además usaremos las Spring Boot DevTools que nos ayudará con reinicios rápidos.

Primero vamos a crear una entidad y para ello creamos una carpeta model.
Tambien creamos un paquete servicio, otro controller y otro repository. A esto le llamamos una estructura orientada a capas.
Los controladores reciben peticiones de http.
Manufacturer es una entidad dentro de model, por es lo anotamos con @Entity, que se almacena en base de datos y por ello lo anotamos con @Table.
No nos olvidamos de crear atributos, y los constructores, y los getters y setters.
A continuación creamos un repositorio: ManufacturerRepository. Como todo gira en torno a las entidades, primero es mejor crear la entidad, y luego la capa de persistencia, y por última lógica de negocio y controlador.
Lo anotamos como @Repository para indicar que es de tipo repositorio.
Con services tambien hacemos otra interface donde podremos manejar la base de datos, para obtener datos (findAll(), etc) y agregandolos a un objeto List<Manufacturer> por ejemplo.
Con un Optional por otro lado, obtendríamos solo un resultado, por lo que debemos de tener cuidado para asegurar un solo resultado en la búsqueda, por ejemplo por Id, o con otro campo que esté anotado con @Column(unique = true).
Todos estos métodos mencionados son de consulta, o RETRIEVE (recuperar).
También podemos hacer un CREATE con save() y un DELETE con deleteById(Long id), y la lógica de negocio que queramos, y dentro del servicio de cada entidad crear su propia lógica.
Al implementar la interfaz implementariamos tambien todos sus métodos. Pero además agregamos una anotación @Service a la clase para mostrar el tipo de Bean que implementamos.
Ahora bien , en la carpeta controller tendremos el contralador, una clase que tendrá la anotación @RestController, que no es lo mismo que @Controller, ya que necesitamos este controller especializado para trabajar con api rest con distintos endspoints o recursos que devolverán información de forma neutra (por ejemplo json), así se podrá interpretar desde el frontend sin problema, desacoplando asi de forma cómoda el cliente del servidor.
En cada método que usemos en el Controller anotaremos @Get... o @Post...
Para ejecutar este programa habria que instalar "postman".
En el archivo application.properties no hemos configurado nada ya que estamos dejando que h2 lo haga, según hemos especificado en el pom.xml
Además tendremos que configurar el esquema en application.properties: para que los logs se vean un poco mejor: spring.main.banner-mode=off, y ajustamos el formato de las trazas de log para que se vea como nos guste y se vea mejor el error: que se vea el nombre de la clase que esté reportando el error en cyan y el mensaje: logging.pattern.console=%clr(%logger{39}){cyan} - %msg%n
Podemos evitar errores si usamos la anotación @Column(name = "nombre_del_campo_en_la_tabla") en la entidad (carpeta modelo) para que se puedan crear las tablas correctamente.
Deberíamos manejar los fallos o excepciones al manejar los "manufacturer" o entidades para poder capturar fallos y resolverlos.
Crear una API REST involucra todas las operaciones si las queremos (Crear, leer, modificar y eliminar...), y evitar nombres reservados (year sería problemático dependiendo de la BBDD que usemos, de modo que podríamos cambiar el nombre de la columna en la anotación @Column(name = "...")) y si no cambiar el application.properties para evitar estos problemas..
Con una aplicación como postman podriamos crear una nueva colección y en el GET poner la url a nuestra api creada y a nuestro repositorio manufacturers: http://localhost:8080/api/manufacturers/1, teniendo nuestro proyecto ejecutándose, y al enviar la peticion veriamos el resultado:
{
"id": 1,
"name": "manufacturer1",
"numEmployees": 1000,
"year": 1990
}
Si queremos enviar datos habría que cambiar a POST en postman, y cambiar a "Body", "raw" y en JSON, cambiando el "name" y en la url enviarlo sib id (en este caso: http://localhost:8080/api/manufacturers), y en el JSON tambien quitar el id, para dejar claro que es una petición nueva:

    "name": "manufacturer2",
    "numEmployees": 1000,
    "year": 1990
}
Creándose entonces el id 2:
{
"id": 2,
"name": "manufacturer2",
"numEmployees": 1000,
"year": 1990
}