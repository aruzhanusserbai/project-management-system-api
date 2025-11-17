package com.example.project.services;

import com.example.project.dto.TaskDto;
import com.example.project.entities.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks();
    TaskDto getTaskById(Long id);
    TaskDto updateTask(Long id, TaskDto updatedTask);
    TaskDto createTask(TaskDto task);
    boolean deleteTask(Long id);
    TaskDto assignEmployee(Long taskId, Long employeeId);
}
