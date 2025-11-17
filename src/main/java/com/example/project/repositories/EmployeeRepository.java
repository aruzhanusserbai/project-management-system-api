package com.example.project.repositories;

import com.example.project.entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
