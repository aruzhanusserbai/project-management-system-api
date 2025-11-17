package com.example.project.mappers;

import com.example.project.dto.TaskDto;
import com.example.project.dto.TaskShortDto;
import com.example.project.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskShortDto toShortDto(Task task);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "project.id", target = "projectId")
    TaskDto toDto(Task task);

    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "projectId", target = "project.id")
    Task toEntity(TaskDto taskDto);
}
