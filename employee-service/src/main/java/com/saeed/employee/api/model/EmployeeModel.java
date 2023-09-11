package com.saeed.employee.api.model;

import java.util.Optional;

public record EmployeeModel(Long id, String fullName, Optional<Double> salary) {
}
