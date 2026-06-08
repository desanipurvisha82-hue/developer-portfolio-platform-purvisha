package com.purvisha.portfolio_backend.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.purvisha.portfolio_backend.entity.Project;
 
@Repository
public interface ProjectRepository
        extends JpaRepository<Project, Long> {
 
}