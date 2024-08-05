package com.JithendraProject.Employee.Service;

import org.springframework.web.multipart.MultipartFile;

import com.JithendraProject.Employee.Entity.Employee_Entity;
import com.JithendraProject.Employee.exception.CurrentUserExecption;

import java.util.List;

public interface EmployeService {
	Employee_Entity createEmploye(Employee_Entity employe, String uuId) throws CurrentUserExecption;
    List<Employee_Entity> getAllEmployeeDetails(String username) throws CurrentUserExecption;
    Employee_Entity getEmployeeById(Long id, String uuId) throws CurrentUserExecption;
    Employee_Entity updateEmployee(Long id, Employee_Entity employee, String uuId) throws CurrentUserExecption;
    void deleteEmployee(Long id, String uuId) throws CurrentUserExecption;
    String saveImage(MultipartFile file);
    byte[] getImage(Long id);
    boolean validateUuId(String uuId) throws CurrentUserExecption;
}
