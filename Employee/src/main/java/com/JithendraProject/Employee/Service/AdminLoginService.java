package com.JithendraProject.Employee.Service;

import java.util.List;

import com.JithendraProject.Employee.Entity.AdminLogin_Entity;
import com.JithendraProject.Employee.Entity.CurrentUserSession;
import com.JithendraProject.Employee.Entity.LogIn;
import com.JithendraProject.Employee.exception.CurrentUserExecption;

public interface AdminLoginService {

    CurrentUserSession logIn(LogIn logIn) throws CurrentUserExecption;

    AdminLogin_Entity createadmin(AdminLogin_Entity adminEntities);

    boolean validateUuId(String uuId) throws CurrentUserExecption;

    List<AdminLogin_Entity> getAlladminDetails(String username) throws CurrentUserExecption;

    String logOut(String uuId) throws CurrentUserExecption;
    
}
