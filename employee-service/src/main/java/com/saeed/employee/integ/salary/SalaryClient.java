package com.saeed.employee.integ.salary;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SalaryClient {

    private final WebClient webClient;

    public SalaryClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Salary> getSalaryByEmployeeId(Long employeeId) {
        return webClient
                .get()
                .uri("/salaries/employee/" + employeeId)
                .retrieve()
                .bodyToMono(Salary.class);
    }

    public Mono<HealthStatus> readinessProbe() {
        return webClient
                .get()
                .uri("/actuator/health/readiness")
                .retrieve()
                .bodyToMono(HealthStatus.class);
    }
}
