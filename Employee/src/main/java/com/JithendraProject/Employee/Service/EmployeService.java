package com.JithendraProject.Employee.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JithendraProject.Employee.Entity.AdminLogin_Entity;
import com.JithendraProject.Employee.Entity.Employee_Entity;
@Service
public interface EmployeService {
	public Employee_Entity createEmploye(Employee_Entity employe);
	public List<Employee_Entity> getAllEmployeeDetails();
	Employee_Entity updateEmployee(Long Id, Employee_Entity employee);
	void deleteEmployee(Long Id);
	public Employee_Entity getEmployeeById(Long id);
	String saveImage(MultipartFile image);
	public byte[] getImage(Long id);
	

}
