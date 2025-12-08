package com.example.dudduk_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DuddukApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuddukApiApplication.class, args);
    }

}
