package com.saeed.employee.model;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .switchIfEmpty(Mono.error(() -> new RuntimeException("Employee not found!")));
    }

    public Flux<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Mono<Employee> editEmployee(Employee employee) {
        return employeeRepository.findById(employee.id())
                .flatMap(e -> employeeRepository.save(employee));
    }

    public Mono<Void> deleteEmployeeById(Long employeeId) {
        return employeeRepository.deleteById(employeeId);
    }
}

