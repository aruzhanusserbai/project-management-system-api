package com.example.project.services.Impl;

import com.example.project.dto.EmployeeDto;
import com.example.project.entities.Employee;
import com.example.project.entities.Project;
import com.example.project.mappers.EmployeeMapper;
import com.example.project.repositories.EmployeeRepository;
import com.example.project.repositories.ProjectRepository;
import com.example.project.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeMapper employeeMapper;


    public List<EmployeeDto> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employees.forEach(employee -> employeeDtos.add(employeeMapper.toDto(employee)));

        return employeeDtos;
    }


    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);

        if(Objects.isNull(employee)){
            return null;
        }

        return employeeMapper.toDto(employee);
    }


    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }


    public EmployeeDto updateEmployee(Long id, EmployeeDto newEmployee){
        Employee employee = employeeRepository.findById(id).orElse(null);

        if(Objects.isNull(employee)){
            return null;
        }

        Employee updated = employeeMapper.toEntity(newEmployee);

        employee.setName(updated.getName());
        employee.setEmail(updated.getEmail());
        employee.setDepartment(updated.getDepartment());
        employee.setTasks(updated.getTasks());
        employee.setProjects(updated.getProjects());

        employeeRepository.save(employee);

        return employeeMapper.toDto(employee);
    }




    public boolean deleteEmployee(Long id){
        employeeRepository.deleteById(id);
        Employee employee = employeeRepository.findById(id).orElse(null);

        return Objects.isNull(employee);
    }


    public EmployeeDto assignProject(Long employeeId, Long projectId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        employee.getProjects().add(project);
        project.getEmployees().add(employee);

        employeeRepository.save(employee);
        projectRepository.save(project);

        return employeeMapper.toDto(employee);
    }

}
