package com.example.springsecuritycifrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityCifradoApplication {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringSecurityCifradoApplication.class, args);

		UserRepository repository =	context.getBean(UserRepository.class); // instancia del repositorio

		PasswordEncoder encoder =	context.getBean(PasswordEncoder.class); // instancia del repositorio

		User user = new User(null, "usuario", encoder.encode("admin"));
		repository.save(user);

	}

}
