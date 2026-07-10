package com.rag_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rag_spring_boot"})
public class RagSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RagSpringBootApplication.class, args);
	}

}
