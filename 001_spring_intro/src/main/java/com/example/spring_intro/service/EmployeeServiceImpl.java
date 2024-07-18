package com.example.spring_intro.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// para indicar que es un bean se crea la anotación con un estereotipo (service, component...)
// @Component
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Override
    public String hello() {
        return "Hola mundo!";
    }
}
