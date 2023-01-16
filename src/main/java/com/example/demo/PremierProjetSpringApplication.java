package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BavardService;

@SpringBootApplication
@RestController
public class PremierProjetSpringApplication {
	
	@Autowired
	private BavardService bavardService;

	public static void main(String[] args) {
		SpringApplication.run(PremierProjetSpringApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String helloWorld () {
		
		return "Hello World" + " " +bavardService.parler();
	}

}