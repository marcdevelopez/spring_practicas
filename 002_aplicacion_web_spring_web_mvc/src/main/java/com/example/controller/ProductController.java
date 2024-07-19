package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// Esta clase controla la carga del modelo en la vista
// Controler se usa para la creación de apps web las cuales la interfaz se genera desde aqui (backend)
@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    /*
    Utilizamos la anotación para hacer GET a la url: http://localhost:8080/products
     */

    // creamos métodos para manejar la interacción del usuario
    @GetMapping
    // Se inyecta el modelo automáticamente
    public String findAll(Model model) {
        List<Product> products = this.repository.findAll();
        // El modelo lo cargamos con datos, pudiendo interactuar con BBDD usando métodos de las librerías:
        model.addAttribute("products", products);
        // Devolvemos una vista
        return "product-List";
    }

    /*
    GET http://Localhost:8080/products/new
    Nos devuelve un formulario vacío
     */
    @GetMapping("/new")
    // Se inyecta el modelo automáticamente
    public String getForm(Model model) {
        // El producto lo cargamos vacío ya que es un formulario
        model.addAttribute("products", new Product());
        // Devolvemos una vista
        return "product-form";
    }

    /*
    POST http://Localhost:8080/products
    Creamos un producto después de enviar el formulario
     */
    @PostMapping
    // Se inyecta el modelo automáticamente
    public String save(@ModelAttribute("product") Product product) {
        this.repository.save(product);
        return "product-form";
    }

    // Otros ejemplos de métodos para el controlador

    /*
    GET http://Localhost:8080/products/{id}/view
     */

    /*
    GET http://Localhost:8080/products/{id}/edit
     */

    /*
    GET http://Localhost:8080/products/{id}/delete
     */

    /*
    GET http://Localhost:8080/products/{id}/delete/all
     */
}
