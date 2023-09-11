package com.saeed.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SalaryClientConfig {

    @Bean
    WebClient webClient(SalaryProperties salaryProperties, WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(salaryProperties.url())
                .build();
    }

}
