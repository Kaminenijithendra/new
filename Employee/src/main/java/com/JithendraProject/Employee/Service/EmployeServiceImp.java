package com.JithendraProject.Employee.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JithendraProject.Employee.Entity.CurrentUserSession;
import com.JithendraProject.Employee.Entity.Employee_Entity;
import com.JithendraProject.Employee.Repository.EmployeeRepository;
import com.JithendraProject.Employee.Repository.SessionRepo;
import com.JithendraProject.Employee.exception.CurrentUserExecption;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImp implements EmployeService {

    @Autowired
    private EmployeeRepository repo;
    
    @Autowired
    private SessionRepo sessionrepo;

    @Override
    public Employee_Entity createEmploye(Employee_Entity employe, String uuId) throws CurrentUserExecption {
        if (!validateUuId(uuId)) {
            throw new CurrentUserExecption("Invalid session ID not found");
        }
        return repo.save(employe);
    }


    @Override
    public List<Employee_Entity> getAllEmployeeDetails(String username) throws CurrentUserExecption {
        Optional<CurrentUserSession> sessionOpt = sessionrepo.findById(username);
        if (!sessionOpt.isPresent()) {
            throw new CurrentUserExecption("Invalid session ID not found");
        }
        String uuId = sessionOpt.get().getUuId();
        if (!validateUuId(uuId)) {
            throw new CurrentUserExecption("Invalid session ID not found");
        }
        return repo.findAll();
    }

    @Override
    public Employee_Entity getEmployeeById(Long id, String uuId) throws CurrentUserExecption {
        validateUuId(uuId);

        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }
    @Override
    public Employee_Entity updateEmployee(Long id, Employee_Entity employee, String uuId) throws CurrentUserExecption {
        validateUuId(uuId);

        Employee_Entity existingEmployee = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setMobileNO(employee.getMobileNO());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setCourses(employee.getCourses());

        if (employee.getImage() != null && employee.getImage().length > 0) {
            existingEmployee.setImage(employee.getImage());
        }

        return repo.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id, String uuId) throws CurrentUserExecption {
        validateUuId(uuId);
        
        repo.deleteById(id);
    }
    @Override
    public String saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot store empty file");
        }

        try {
            byte[] bytes = file.getBytes();
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
    
    @Override
    public byte[] getImage(Long id) {
        Employee_Entity employee = repo.findById(id).orElse(null);
        if (employee != null) {
            return employee.getImage();
        }
        return null;
    }

    @Override
    public boolean validateUuId(String uuId) throws CurrentUserExecption {
        Optional<CurrentUserSession> sessionOpt = sessionrepo.findByUuId(uuId);
        return sessionOpt.isPresent();
    }
}
