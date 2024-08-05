package com.JithendraProject.Employee.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.JithendraProject.Employee.exception.CurrentUserExecption;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/employe")
public class EmployeeController {

    @Autowired
    EmployeService service;

    @PostMapping("/create/{uuId}")
    public ResponseEntity<Employee_Entity> createEmploye(@PathVariable("uuId") String uuId,
                                                          @RequestParam("name") String name,
                                                          @RequestParam("email") String email,
                                                          @RequestParam("mobileNO") String mobileNO,
                                                          @RequestParam("designation") String designation,
                                                          @RequestParam("gender") String gender,
                                                          @RequestParam("courses") String courses,
                                                          @RequestParam("image") MultipartFile image) {
        try {
            // Validate the UUID
            if (!service.validateUuId(uuId)) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

            // Create employee entity
            Employee_Entity employe = new Employee_Entity();
            employe.setName(name);
            employe.setEmail(email);
            employe.setMobileNO(mobileNO);
            employe.setDesignation(designation);
            employe.setGender(gender);
            employe.setCourses(courses);

            // Set image if provided
            if (image != null && !image.isEmpty()) {
                employe.setImage(image.getBytes());
            }

            // Create and save employee
            Employee_Entity createdEmployee = service.createEmploye(employe, uuId);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (CurrentUserExecption | IOException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/getallemployees/{username}")
    public ResponseEntity<List<Employee_Entity>> getAllEmployeeDetails(@PathVariable String username) {
        try {
            List<Employee_Entity> employeeList = service.getAllEmployeeDetails(username);
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (CurrentUserExecption e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}/{uuId}")
    public ResponseEntity<Employee_Entity> getEmployeeById(@PathVariable Long id, @PathVariable String uuId) {
        try {
            Employee_Entity employee = service.getEmployeeById(id, uuId);
            return ResponseEntity.ok(employee);
        } catch (CurrentUserExecption e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/updatebyid/{id}/{uuId}")
    public ResponseEntity<Employee_Entity> updateEmployee(@PathVariable Long id,
                                                          @PathVariable String uuId,
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

        try {
            Employee_Entity updatedEmployee = service.updateEmployee(id, employee, uuId);
            return ResponseEntity.ok(updatedEmployee);
        } catch (CurrentUserExecption e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping("/delete/{id}/{uuId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id, @PathVariable String uuId) {
        try {
            service.deleteEmployee(id, uuId);
            return ResponseEntity.ok("Employee with id " + id + " deleted successfully");
        } catch (CurrentUserExecption e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
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
