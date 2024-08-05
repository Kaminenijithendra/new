package com.JithendraProject.Employee.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JithendraProject.Employee.Entity.AdminLogin_Entity;
import com.JithendraProject.Employee.Entity.CurrentUserSession;
import com.JithendraProject.Employee.Entity.LogIn;
import com.JithendraProject.Employee.Repository.AdminloginRepository;
import com.JithendraProject.Employee.Repository.SessionRepo;
import com.JithendraProject.Employee.exception.CurrentUserExecption;
@Service
public class AdminLoginServiceImp implements AdminLoginService {
	@Autowired
    private AdminloginRepository adminloginrepository;
	
	@Autowired
	private SessionRepo sessionrepo;
	
	/*
	 * @Override public boolean authenticateUser(String username, String password) {
	 * Optional<AdminLogin_Entity> user =
	 * adminloginrepository.findByUsername(username); return user != null &&
	 * user.getPassword().equals(password); }
	 */
	
	@Override
	public AdminLogin_Entity createadmin(AdminLogin_Entity adminentites) {


		AdminLogin_Entity saved_admin=adminloginrepository.save(adminentites);
		return saved_admin;
	}
	
	
	@Override
	public boolean validateUuId(String uuId) throws CurrentUserExecption {
	    Optional<CurrentUserSession> sessionOpt = sessionrepo.findByUuId(uuId);
	    return sessionOpt.isPresent();
	}

	@Override
	public List<AdminLogin_Entity> getAlladminDetails(String username) throws CurrentUserExecption {
	    Optional<CurrentUserSession> sessionOpt = sessionrepo.findById(username);
	    if (!sessionOpt.isPresent()) {
	        throw new CurrentUserExecption("Invalid session ID not found");
	    }
	    String uuId = sessionOpt.get().getUuId();
	    if (!validateUuId(uuId)) {
	        throw new CurrentUserExecption("Invalid session ID not found");
	    } 
	    return adminloginrepository.findAll();
	}

	@Override
	public CurrentUserSession logIn(LogIn logIn) throws CurrentUserExecption {
	    Optional<AdminLogin_Entity> optionalUser = adminloginrepository.findByUsername(logIn.getUsername());
	    if (optionalUser.isPresent()) {
	        AdminLogin_Entity user = optionalUser.get();

	        if (user.getPassword().equals(logIn.getPassword())) {
	            Optional<CurrentUserSession> optionalSession = sessionrepo.findById(logIn.getUsername());

	            CurrentUserSession session;
	            if (optionalSession.isEmpty()) {
	                String uuid = this.randomString();
	                session = new CurrentUserSession(logIn.getUsername(), user.getId(), uuid);
	                session = sessionrepo.save(session);
	            } else {
	                session = optionalSession.get();
	                session.setUuId(session.getUuId()); // Update UUID if needed
	                sessionrepo.save(session);
	            }

	            return session;
	        } else {
	            throw new CurrentUserExecption("Password is Wrong");
	        }
	    } else {
	        throw new CurrentUserExecption("User Name is Wrong");
	    }
	}



	@Override
	public String logOut(String uuId) throws CurrentUserExecption {
		Optional<CurrentUserSession> optionalSession=sessionrepo.findByUuId(uuId);
		System.out.println(uuId +"in logout");
		if(optionalSession.isPresent()) {
			
			CurrentUserSession session = optionalSession.get();
			sessionrepo.delete(session);
			
			return "LogOut " + session.getUsername();
			
		}else {
			throw new CurrentUserExecption("wrong UUID");
		}
		
		
		
	}
	
	
	
	private String randomString() {
		String alphabets="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int length =6;
		StringBuilder str= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<length;i++) {
			int index=random.nextInt(alphabets.length());
			
			
			str.append(alphabets.charAt(index));
		}
		return str.toString();
	}
}
