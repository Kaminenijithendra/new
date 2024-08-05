package com.JithendraProject.Employee.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.JithendraProject.Employee.Entity.AdminLogin_Entity;
import com.JithendraProject.Employee.Entity.CurrentUserSession;
import com.JithendraProject.Employee.Entity.LogIn;
import com.JithendraProject.Employee.Service.AdminLoginService;
import com.JithendraProject.Employee.exception.CurrentUserExecption;



@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;
    
    @PostMapping("/login")
    public ResponseEntity<CurrentUserSession> logIn(@RequestBody LogIn logIn) throws CurrentUserExecption{
    	
    	CurrentUserSession session = adminLoginService.logIn(logIn);
    	
    	return new ResponseEntity<CurrentUserSession>(session, HttpStatus.OK);
    	
    }
    
    
//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestBody AdminLogin_Entity request) {
//        String username = request.getUsername();
//        String password = request.getPassword();
//
//        if (adminLoginService.authenticateUser(username, password)) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", "Bearer some-jwt-token"); // Replace with actual token generation
//            
//            // Return JSON response
//            return ResponseEntity.ok().headers(headers).body("{\"message\": \"Login successful\"}");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid username or password\"}");
//        }
//    }

    @PostMapping("/create")
    public ResponseEntity<AdminLogin_Entity> createAdmin(@RequestBody AdminLogin_Entity adminEntities) {
        AdminLogin_Entity createdAdmin = adminLoginService.createadmin(adminEntities);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }
        @GetMapping("/getalladmin/{username}")
        public ResponseEntity<List<AdminLogin_Entity>> getAllAdminDetails(@PathVariable String username) throws CurrentUserExecption {
            try {
                List<AdminLogin_Entity> adminList = adminLoginService.getAlladminDetails(username);
                return new ResponseEntity<>(adminList, HttpStatus.OK);
            } catch (CurrentUserExecption e) {
            	throw new CurrentUserExecption("Invalid session ID not found");
            }
        }
        
        
    @DeleteMapping("/logOut/{uuId}")
    public ResponseEntity<String> logOut(@PathVariable("uuId") String  uuId) throws CurrentUserExecption{
    	System.out.println(uuId +"in contolaer");
    	String message = adminLoginService.logOut(uuId);
    	
    	return new ResponseEntity<String>(message, HttpStatus.OK);
    	
    }
}
