package com.saeed.salary;

import com.saeed.salary.config.DevContainersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevSalaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(SalaryServiceApplication::main)
                .with(DevContainersConfiguration.class)
                .run(args);
    }
}
