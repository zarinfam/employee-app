package com.saeed.employee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "salary-service")
public record SalaryProperties(String url) {
}
