package com.example.project.mappers;

import com.example.project.dto.ProjectDto;
import com.example.project.dto.ProjectShortDto;
import com.example.project.entities.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectShortDto toShortDto(Project project);
    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto projectDto);
}
