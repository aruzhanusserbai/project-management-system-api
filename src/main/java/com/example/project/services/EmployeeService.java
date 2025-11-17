package com.example.project.services;


import com.example.project.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployeeById(Long Id);
    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(Long id, EmployeeDto employee);
    boolean deleteEmployee(Long id);
    EmployeeDto assignProject(Long employeeId, Long projectId);
}
