package com.JithendraProject.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JithendraProject.Employee.Entity.AdminLogin_Entity;

@Repository
public interface AdminloginRepository extends JpaRepository<AdminLogin_Entity, Long> {
    AdminLogin_Entity findByUsername(String username); // Update method name to findByUsername
}
