package com.example.project.services.Impl;

import com.example.project.dto.ProjectDto;
import com.example.project.entities.Employee;
import com.example.project.entities.Project;
import com.example.project.entities.Task;
import com.example.project.mappers.ProjectMapper;
import com.example.project.mappers.TaskMapper;
import com.example.project.repositories.EmployeeRepository;
import com.example.project.repositories.ProjectRepository;
import com.example.project.repositories.TaskRepository;
import com.example.project.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectMapper projectMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ProjectDto> getProjects(){
        List<ProjectDto> projects = new ArrayList<>();
        projectRepository.findAll().forEach(project -> projects.add(projectMapper.toDto(project)));
        return projects;
    }


    public ProjectDto getProjectById(Long id){
        Project project = projectRepository.findById(id).orElse(null);

        if(Objects.isNull(project)){
            return null;
        }

        return projectMapper.toDto(project);
    }


    public ProjectDto updateProject(Long id, ProjectDto updatedProject){
        Project project = projectRepository.findById(id).orElse(null);

        if(Objects.isNull(project)){
            return null;
        }

        List<Employee> employees = new ArrayList<>();
        updatedProject.getEmployees().forEach(employeeShortDto -> employees.add(employeeRepository
                .findById(employeeShortDto.id).orElseThrow())
        );

        List<Task> tasks = new ArrayList<>();
        updatedProject.getTasks().forEach(taskShortDto -> tasks.add(taskRepository
                .findById(taskShortDto.id).orElseThrow())
        );

        project.setName(updatedProject.getName());
        project.setStatus(updatedProject.getStatus());
        project.setEmployees(employees);
        project.setTasks(tasks);
        project.setStartTime(updatedProject.getStartTime());
        project.setEndTime(updatedProject.getEndTime());

        projectRepository.save(project);

        return projectMapper.toDto(project);

    }


    public ProjectDto createProject(ProjectDto project){
        Project project1 = projectMapper.toEntity(project);
        projectRepository.save(project1);
        return projectMapper.toDto(project1);
    }


    public boolean deleteProject(Long id){
        projectRepository.deleteById(id);
        Project check = projectRepository.findById(id).orElse(null);

        return Objects.isNull(check);
    }


    public ProjectDto addTaskToProject(Long projectId, Long taskId){
        Project project = projectRepository.findById(projectId).orElseThrow();
        Task task = taskRepository.findById(taskId).orElseThrow();

        project.getTasks().add(task);
        task.setProject(project);

        projectRepository.save(project);
        taskRepository.save(task);

        return projectMapper.toDto(project);

    }

}
