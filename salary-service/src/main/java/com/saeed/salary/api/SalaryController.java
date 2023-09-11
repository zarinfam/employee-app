package com.saeed.salary.api;

import com.saeed.salary.model.Salary;
import com.saeed.salary.model.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/employee/{employeeId}")
    public Mono<Salary> getSalariesByEmployeeId(@PathVariable Long employeeId) {
        return salaryService.findEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Salary> createSalary(@RequestBody Salary salary) {
        return salaryService.saveSalary(salary);
    }
}
