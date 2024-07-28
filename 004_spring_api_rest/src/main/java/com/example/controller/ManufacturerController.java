package com.example.controller;

import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Es necesario especificar en la api en la que se escucha
@RequestMapping("/api")
public class ManufacturerController {

    private ManufacturerService service;

    // continuamos con la implementación limpia mediante el constructor para facilitar el testing con Mok por ejemplo.
    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }

    /*
    Accesible mediante GET http://localhost:8080/api/manufacturers
     */
    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> findAll(){
        List<Manufacturer> manufacturers = this.service.findAll();
        if (manufacturers.isEmpty())
            return ResponseEntity.notFound().build();
        // devolvemos todos los fabricantes
        return ResponseEntity.ok(this.service.findAll());
    }

    /*
   Accesible mediante GET http://localhost:8080/api/manufacturers/year/1990
   Las urls no se pueden repetir, deben de ser únicas, sino daría error por duplicar url
    */
    @GetMapping("/manufacturers/year/{year}")
    public ResponseEntity<List<Manufacturer>> findAllByYear(@PathVariable Integer year){
        List<Manufacturer> manufacturers = this.service.findAllByYear(year);
        if (manufacturers.isEmpty())
            return ResponseEntity.notFound().build();
        // devolvemos todos los fabricantes
        return ResponseEntity.ok(manufacturers);
    }

    /*
    GET http://localhost:8080/api/manufacturers/3
    si no existe el fabricante 3, devolverá un 404
     */
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        // Para gestionar un 404: con programación funcional en la que combinamos lambdas
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // El notFound sería un 404

    }

    // Ahora creamos POST, separamos Crear de Actualizar
    @PostMapping("/manufacturers") // Cuando llamemos a /manufacturers enviamos en el cuerpo un fabricante, que irá
    // incluido en el objeto manufacturer
    public ResponseEntity<Manufacturer> create(@RequestBody Manufacturer manufacturer){
        // Comprovaciones a implementar...
        if(manufacturer.getId() != null)
            return ResponseEntity.badRequest().build();
        this.service.save(manufacturer);
        return ResponseEntity.ok(manufacturer); // Lo devolverá con un id
    }

    @PutMapping("/manufacturers") // Se utilizarán métodos http distintos de modo que no habrá repetición
    public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer){
        this.service.save(manufacturer);
        return ResponseEntity.ok(manufacturer);
    }

    // Ojo, para el método delete usar anotación:
    @DeleteMapping("/manufacturers/{identifier}") // Deberán coincidir los id del método con el de la url
    public ResponseEntity<Manufacturer> deleteById(@PathVariable("identifier") Long id){ // Si no coinciden se asignará
        // el valor poniendo el mismo valor en @PathVariable()
        this.service.deleteById(id);
        return ResponseEntity.noContent().build(); // Como el dato ya no existe devolvemos un "no contenido", se ha borrado
    }
}
