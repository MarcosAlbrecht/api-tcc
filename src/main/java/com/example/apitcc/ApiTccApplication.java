package com.example.apitcc;

import com.example.apitcc.repository.UsuarioRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UsuarioRepository.class)

public class ApiTccApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTccApplication.class, args);
	}

}

