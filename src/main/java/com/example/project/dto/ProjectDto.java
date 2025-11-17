package com.example.project.dto;

import com.example.project.entities.Employee;
import com.example.project.entities.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ProjectDto {
    private Long id;
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    private String status;
    private List<EmployeeShortDto> employees;
    private List<TaskShortDto> tasks;
}
