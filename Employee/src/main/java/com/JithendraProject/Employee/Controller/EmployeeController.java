package com.JithendraProject.Employee.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.JithendraProject.Employee.Entity.Employee_Entity;
import com.JithendraProject.Employee.Service.EmployeService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/employe")
public class EmployeeController {

    @Autowired
    EmployeService service;

    @PostMapping("/create")
    public Employee_Entity createEmploye(@RequestParam("name") String name,
                                         @RequestParam("email") String email,
                                         @RequestParam("mobileNO") String mobileNO,
                                         @RequestParam("designation") String designation,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("courses") String courses,
                                         @RequestParam("image") MultipartFile image) {

        Employee_Entity employe = new Employee_Entity();
        employe.setName(name);
        employe.setEmail(email);
        employe.setMobileNO(mobileNO);
        employe.setDesignation(designation);
        employe.setGender(gender);
        employe.setCourses(courses);

        try {
            if (image != null && !image.isEmpty()) {
                employe.setImage(image.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return service.createEmploye(employe);
    }

    @GetMapping("/getallemployees")
    public List<Employee_Entity> getAllEmployeDetails() {
        return service.getAllEmployeeDetails();
    }

    @GetMapping("/{id}")
    public Employee_Entity getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Employee_Entity> updateEmployee(@PathVariable Long id,
                                                          @RequestParam("name") String name,
                                                          @RequestParam("email") String email,
                                                          @RequestParam("mobileNO") String mobileNO,
                                                          @RequestParam("designation") String designation,
                                                          @RequestParam("gender") String gender,
                                                          @RequestParam("courses") String courses,
                                                          @RequestParam(value = "image", required = false) MultipartFile image) {
        Employee_Entity employee = new Employee_Entity();
        employee.setName(name);
        employee.setEmail(email);
        employee.setMobileNO(mobileNO);
        employee.setDesignation(designation);
        employee.setGender(gender);
        employee.setCourses(courses);

        try {
            if (image != null && !image.isEmpty()) {
                employee.setImage(image.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Employee_Entity updatedEmployee = service.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok("Employee with id " + id + " deleted successfully");
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] image = service.getImage(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
                    .body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}