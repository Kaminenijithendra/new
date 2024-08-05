package com.JithendraProject.Employee.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JithendraProject.Employee.Entity.AdminLogin_Entity;
import java.util.List;


@Repository
public interface AdminloginRepository extends JpaRepository<AdminLogin_Entity, Long> {
    public Optional<AdminLogin_Entity> findByUsername(String username); // Update method name to findByUsername
}
