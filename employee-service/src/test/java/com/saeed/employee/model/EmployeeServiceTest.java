package com.saeed.employee.model;

import com.saeed.employee.api.model.EmployeeModel;
import com.saeed.employee.integ.salary.Salary;
import com.saeed.employee.integ.salary.SalaryClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private SalaryClient salaryClient;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void findEmployeeById_success() {
        // Given
        Long employeeId = 1L;
        var employee = new Employee(employeeId, "Saeed");
        var salary = new Salary(1L, employeeId, 100.0);

        when(employeeRepository.findById(employeeId))
                .thenReturn(Mono.just(employee));
        when(salaryClient.getSalaryByEmployeeId(employeeId))
                .thenReturn(Mono.just(salary));

        // When
        var employeeModel = employeeService.findEmployeeById(employeeId);

        // Then
        StepVerifier.create(employeeModel)
                .expectNext(new EmployeeModel(employeeId, "Saeed", Optional.of(100.0)))
                .verifyComplete();
    }
}
