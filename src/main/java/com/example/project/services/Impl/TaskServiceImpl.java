package com.example.project.services.Impl;

import com.example.project.dto.TaskDto;
import com.example.project.entities.Employee;
import com.example.project.entities.Project;
import com.example.project.entities.Task;
import com.example.project.mappers.TaskMapper;
import com.example.project.repositories.EmployeeRepository;
import com.example.project.repositories.ProjectRepository;
import com.example.project.repositories.TaskRepository;
import com.example.project.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;

    public List<TaskDto> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> tasksDto = new ArrayList<>();
        tasks.forEach(task ->tasksDto.add(taskMapper.toDto(task)));

        return tasksDto;
    }


    public TaskDto getTaskById(Long id){
        Task task = taskRepository.findById(id).orElse(null);

        if(Objects.isNull(task)){
            return null;
        }
        return taskMapper.toDto(task);
    }


    public TaskDto updateTask(Long id, TaskDto updatedTask){
        Task task = taskRepository.findById(id).orElse(null);

        if(Objects.isNull(task)){
            return null;
        }

        Employee employee = employeeRepository.findById(updatedTask.getEmployeeId()).orElseThrow();
        Project project = projectRepository.findById(updatedTask.getProjectId()).orElseThrow();

        task.setTitle(updatedTask.getTitle());
        task.setDeadline(updatedTask.getDeadline());
        task.setDescription(updatedTask.getDescription());
        task.setEmployee(employee);
        task.setProject(project);
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());

        taskRepository.save(task);

        return taskMapper.toDto(task);
    }


    public TaskDto createTask(TaskDto task){
        Task task1 = taskMapper.toEntity(task);
        taskRepository.save(task1);
        return taskMapper.toDto(task1);
    }


    public boolean deleteTask(Long id){
        taskRepository.deleteById(id);
        Task check = taskRepository.findById(id).orElse(null);

        if(Objects.isNull(check)){
            return true;
        }else {
            return false;
        }
    }


    public TaskDto assignEmployee(Long taskId, Long employeeId){
        Task task = taskRepository.findById(taskId).orElseThrow();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        task.setEmployee(employee);
        employee.getTasks().add(task);

        taskRepository.save(task);
        employeeRepository.save(employee);

        return taskMapper.toDto(task);
    }
}
