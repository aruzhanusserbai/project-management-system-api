package com.example.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private String department;
    private List<ProjectShortDto> projects;
    private List<TaskShortDto> tasks;
}
