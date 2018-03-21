package com.crypto.electrimate.hardware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan({"com.crypto.electrimate.hardware.*"})
@EntityScan({"com.crypto.electrimate.hardware.entity.*"})
@EnableJpaRepositories({"com.crypto.electrimate.hardware.repository.*"})
public class HardwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HardwareApplication.class, args);
    }
}
