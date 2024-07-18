package com.example.spring_intro.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Primary
//@Service
public class CustomerBServiceImpl implements CustomerService{
    @Override
    public String hello() {
        return "Hello from customer B";
    }
}
