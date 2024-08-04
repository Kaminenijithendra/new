package com.JithendraProject.Employee.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.JithendraProject.Employee.Entity.AdminLogin_Entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public interface AdminLoginService{
	boolean authenticateUser(String username, String password);
	public AdminLogin_Entity createadmin(AdminLogin_Entity adminentites);
	public List<AdminLogin_Entity> getAlladminDetails();
}
