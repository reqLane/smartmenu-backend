package com.naukma.smartmenubackend.employee;

import com.naukma.smartmenubackend.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo userRepo) {
        this.employeeRepo = userRepo;
    }

    // BUSINESS LOGIC

    // CRUD OPERATIONS

    public Optional<Employee> findByEmail(String email) {
        return employeeRepo.findByEmailEquals(email);
    }
}
