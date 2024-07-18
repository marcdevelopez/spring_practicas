package com.example.spring_intro;

import com.example.spring_intro.controller.EmployeeControler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Con la anotación ComponentScan se obliga a escanear los demás paquetes
@ComponentScan({"com.example.spring_intro","com.example2.spring_intro"})
public class SpringIntroApplication {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(SpringIntroApplication.class, args);
		var employeeControler = context.getBean(EmployeeControler.class);
		System.out.println(employeeControler.helloFromEmployeeService());
		System.out.println(employeeControler.helloFromCustomerService());
	}

}
