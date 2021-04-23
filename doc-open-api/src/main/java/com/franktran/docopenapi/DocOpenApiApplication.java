package com.franktran.docopenapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DocOpenApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocOpenApiApplication.class, args);
    }

}
