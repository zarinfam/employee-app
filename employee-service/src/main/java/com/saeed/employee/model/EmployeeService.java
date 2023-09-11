package com.saeed.employee.model;

import com.saeed.employee.api.model.EmployeeModel;
import com.saeed.employee.integ.salary.Salary;
import com.saeed.employee.integ.salary.SalaryClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SalaryClient salaryClient;

    public EmployeeService(EmployeeRepository employeeRepository, SalaryClient salaryClient) {
        this.employeeRepository = employeeRepository;
        this.salaryClient = salaryClient;
    }

    public Mono<EmployeeModel> findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .flatMap(employee -> mapToModel(employee, salaryClient.getSalaryByEmployeeId(employeeId)))
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

    private Mono<EmployeeModel> mapToModel(Employee employee, Mono<Salary> salary) {
        return salary.map(s -> new EmployeeModel(employee.id(), employee.fullName(), Optional.of(s.salary())))
                .switchIfEmpty(Mono.just(new EmployeeModel(employee.id(), employee.fullName(), Optional.empty())));
    }
}

