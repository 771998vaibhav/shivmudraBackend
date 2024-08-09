package com.shivmudra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication (exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		 org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
@ComponentScan({"com.shivmudra","com.shivmudra.controller","com.shivmudra.dto","com.shivmudra.model","com.shivmudra.model.dao","com.shivmudra.service","com.shivmudra.service.impl","com.shivmudra.services"})
public class ShivMudraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShivMudraApplication.class, args);
	}

}
