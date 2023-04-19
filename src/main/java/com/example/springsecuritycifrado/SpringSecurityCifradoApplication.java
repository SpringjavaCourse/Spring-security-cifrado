package com.example.springsecuritycifrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringSecurityCifradoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringSecurityCifradoApplication.class, args);

		UserRepository repository =	context.getBean(UserRepository.class); // instancia del repositorio

		User user = new User(null, "usuario", "admin");
		repository.save(user);

	}

}
