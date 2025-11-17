package com.example.project.controllers;

import com.example.project.dto.EmployeeDto;
import com.example.project.services.Impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<EmployeeDto> employees = employeeService.getEmployees();

        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(employees);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        EmployeeDto employee = employeeService.getEmployeeById(id);

        if(Objects.isNull(employee)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(employee);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employee){
        EmployeeDto createdEmployee = employeeService.createEmployee(employee);

        if(Objects.isNull(createdEmployee)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(createdEmployee);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee){
        EmployeeDto employeeDto = employeeService.updateEmployee(id, employee);

        if(Objects.isNull(employeeDto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(employeeDto);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        boolean check = employeeService.deleteEmployee(id);

        if(check){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("{employeeId}/assignProject/{projectId}")
    public ResponseEntity<?> assignProject(@PathVariable Long employeeId, @PathVariable Long projectId){
        EmployeeDto employeeDto = employeeService.assignProject(employeeId, projectId);

        if(Objects.isNull(employeeDto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(employeeDto);
        }
    }
}
