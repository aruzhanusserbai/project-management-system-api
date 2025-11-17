package com.example.project.services;

import com.example.project.dto.ProjectDto;
import com.example.project.entities.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getProjects();
    ProjectDto getProjectById(Long id);
    ProjectDto updateProject(Long id, ProjectDto updatedProject);
    ProjectDto createProject(ProjectDto project);
    boolean deleteProject(Long id);
    ProjectDto addTaskToProject(Long projectId, Long taskId);

}
