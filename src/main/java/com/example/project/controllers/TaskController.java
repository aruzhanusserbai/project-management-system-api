package com.example.project.controllers;


import com.example.project.dto.TaskDto;
import com.example.project.services.Impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskServiceImpl taskService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TaskDto> tasks = taskService.getTasks();

        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(tasks);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TaskDto task = taskService.getTaskById(id);

        if(Objects.isNull(task)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(task);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskDto task){
        TaskDto createdTask = taskService.createTask(task);

        if(Objects.isNull(createdTask)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(createdTask);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDto updatedTask){
        TaskDto task = taskService.updateTask(id, updatedTask);

        if(Objects.isNull(task)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(task);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        boolean check = taskService.deleteTask(id);

        if(check){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("{taskId}/assign/{employeeId}")
    public ResponseEntity<?> assignEmployee(@PathVariable Long taskId, @PathVariable Long employeeId){
        TaskDto task = taskService.assignEmployee(taskId, employeeId);

        if(Objects.isNull(task)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(task);
        }
    }
}
