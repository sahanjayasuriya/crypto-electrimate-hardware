package com.crypto.electrimate.hardware;

import com.crypto.electrimate.hardware.service.ArduinoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Configuration
@ComponentScan({"com.crypto.electrimate.hardware"})
@EntityScan({"com.crypto.electrimate.hardware.entity"})
@EnableJpaRepositories({"com.crypto.electrimate.hardware.repository"})
@EnableAsync
public class HardwareApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext c = SpringApplication.run(HardwareApplication.class, args);
        ArduinoService as = c.getBean(ArduinoService.class);
        as.testAsync();
        System.out.println("AFTER ASYNC");
    }
}
