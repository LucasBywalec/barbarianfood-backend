package com.barbarian.barbarianfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
})
@EntityScan("com.barbarian.barbarianfood.entity")
@EnableJpaRepositories("com.barbarian.barbarianfood.repository")
public class BarbarianfoodApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BarbarianfoodApplication.class, args);
    }

}
