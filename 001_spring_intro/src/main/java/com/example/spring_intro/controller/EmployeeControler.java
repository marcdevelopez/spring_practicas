package com.example.spring_intro.controller;
import com.example.spring_intro.primary.CustomerService;
import com.example.spring_intro.service.EmployeeService;
import com.example2.spring_intro.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeControler {

    // con Autowired se llama automaticamente a los objetos, son las "inyecciones"
    //@Autowired
    private EmployeeService employeeService;

    //@Autowired
    private CustomerService customerService;

    /*
otra opción es utilizar @Qualifier("CustomerBServiceImpl")
 */

    //@Autowired
    private HelloService helloService;

    public EmployeeControler(EmployeeService employeeService, CustomerService customerService, HelloService helloService)
    {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.helloService = helloService;
    }

    public String helloFromEmployeeService(){
        return this.employeeService.hello();

    }
    public String helloFromCustomerService(){
        return this.customerService.hello();
    }
}
