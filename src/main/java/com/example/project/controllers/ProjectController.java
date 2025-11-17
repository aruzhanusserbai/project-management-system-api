package com.example.project.controllers;

import com.example.project.dto.ProjectDto;
import com.example.project.services.Impl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectServiceImpl projectService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ProjectDto> projects = projectService.getProjects();

        if(projects.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(projects);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        ProjectDto project = projectService.getProjectById(id);

        if(Objects.isNull(project)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(project);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectDto project){
        ProjectDto createdProject = projectService.createProject(project);

        if(Objects.isNull(createdProject)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(createdProject);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectDto project){
        ProjectDto updateProject = projectService.updateProject(id, project);

        if(Objects.isNull(updateProject)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(updateProject);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        boolean check = projectService.deleteProject(id);

        if(check){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping("{projectId}/addTask/{taskId}")
    public ResponseEntity<?> addTaskProject(@PathVariable Long projectId, @PathVariable Long taskId){
        ProjectDto project = projectService.addTaskToProject(projectId, taskId);

        if(Objects.isNull(project)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(project);
        }
    }
}
