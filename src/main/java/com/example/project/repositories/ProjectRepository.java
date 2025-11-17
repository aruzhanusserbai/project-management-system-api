package com.example.project.repositories;

import com.example.project.entities.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
