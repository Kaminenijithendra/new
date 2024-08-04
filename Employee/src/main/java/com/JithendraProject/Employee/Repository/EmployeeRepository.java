package com.JithendraProject.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JithendraProject.Employee.Entity.Employee_Entity;

import java.awt.Image;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee_Entity, Long> {


}
