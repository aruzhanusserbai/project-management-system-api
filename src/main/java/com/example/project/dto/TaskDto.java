package com.example.project.dto;

import com.example.project.entities.Employee;
import com.example.project.entities.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDate deadline;
    private Long projectId;
    private Long employeeId;
}
