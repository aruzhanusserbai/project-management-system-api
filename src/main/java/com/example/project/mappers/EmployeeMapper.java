package com.example.project.mappers;

import com.example.project.dto.EmployeeDto;
import com.example.project.dto.EmployeeShortDto;
import com.example.project.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeShortDto toShortDto(Employee employee);
    EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeDto employeeDto);
}
