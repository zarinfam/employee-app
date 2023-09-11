package com.saeed.salary.model;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface SalaryRepository extends R2dbcRepository<Salary, Long> {
    Mono<Salary> findByEmployeeId(Long employeeId);
}
