package com.example.spring_intro.config;

import com.example.spring_intro.primary.CustomerAServiceImpl;
import com.example.spring_intro.primary.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:beans.xml")
public class BeansConfig {

    /**
     *     si creamos el bean aquí no es necesario agregar las anotaciones
     *      @Primary ni @Service en las clases para crear los beans.
     *      Esta opción se utiliza para configurar clases que provienen de frameworks
     *      externos.
     */
    @Bean
    public CustomerService customerService(){
        return  new CustomerAServiceImpl();
    }

}
