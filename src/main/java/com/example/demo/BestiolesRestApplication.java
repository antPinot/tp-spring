package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class BestiolesRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(BestiolesRestApplication.class, args);

	}

}
