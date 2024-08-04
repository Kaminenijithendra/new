package com.JithendraProject.Employee.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JithendraProject.Employee.Entity.AdminLogin_Entity;
import com.JithendraProject.Employee.Repository.AdminloginRepository;
@Service
public class AdminLoginServiceImp implements AdminLoginService {
	@Autowired
    private AdminloginRepository adminloginrepository;
	
	@Override
    public boolean authenticateUser(String username, String password) {
        AdminLogin_Entity user = adminloginrepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
	
	
	
	public AdminLogin_Entity createadmin(AdminLogin_Entity adminentites) {


		AdminLogin_Entity saved_admin=adminloginrepository.save(adminentites);
		return saved_admin;
	}

	@Override
	public List<AdminLogin_Entity> getAlladminDetails() {
		List<AdminLogin_Entity> adminEntities=adminloginrepository.findAll();
		return adminEntities;
	}
}
