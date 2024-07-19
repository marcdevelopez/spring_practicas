package com.example;

import com.example.controller.ProductController;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// Carga los productos en el arraque
		ApplicationContext context = SpringApplication.run(App.class, args);
		// Con el repositorio podremos crear productos
		var repository = context.getBean(ProductRepository.class);

		List<Product> products = List.of(
				new Product(null,"product1", 5.99,1),
				new Product(null,"product2", 10.99,2),
				new Product(null,"product3", 7.99,5),
				new Product(null,"product4", 9.99,3),
				new Product(null,"product5", 9.99,3),
				new Product(null,"product6", 9.99,3)

		);
		repository.saveAll(products);
	}

}
