package com.saeed.employee.config;

import com.saeed.employee.integ.salary.SalaryClient;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class EmployeeServiceConfig {

    @Bean(name = "salaryHealthIndicator")
    public ReactiveHealthIndicator salaryHealthIndicator(SalaryClient salaryClient) {
        return () -> salaryClient.readinessProbe()
                .map(status -> Status.UP.getCode().equalsIgnoreCase(status.status()) ? Health.up().build() : buildDownWithReason("Salary service is not ready!"))
                .onErrorResume(e -> Mono.just(buildDownWithReason("Error occurred during the call Salary Service readinessProbe")));
    }

    private Health buildDownWithReason(String reason) {
        return Health.down().withDetail("reason", reason).build();
    }

}
