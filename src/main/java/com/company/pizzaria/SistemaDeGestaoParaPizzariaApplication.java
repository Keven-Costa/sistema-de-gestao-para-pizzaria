package com.company.pizzaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.company.pizzaria.util.EnvLoader;



@Configuration
@EnableAutoConfiguration

@SpringBootApplication
public class SistemaDeGestaoParaPizzariaApplication {

    public static void main(String[] args) {
    	EnvLoader.load();
        SpringApplication.run(SistemaDeGestaoParaPizzariaApplication.class, args);
    }
}
