package com.cuidadodemascotas.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "com.cuidadodemascotas.microservice",
        "org.example.cuidadodemascota.commons"
})
@EnableJpaRepositories(basePackages = {
        "com.cuidadodemascotas.microservice.repository"
})
@EntityScan(basePackages = {
        "org.example.cuidadodemascota.commons.entities"
})
public class MicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

}


