package com.JithendraProject.Employee.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CurrentUserSession {
	
	
	
	public CurrentUserSession() {
		
	}
	public CurrentUserSession(String username, Long userId, String uuId) {
		super();
		this.username = username;
		this.userId = userId;
		this.uuId = uuId;
	}
	@Override
	public String toString() {
		return "CurrentUserSession [username=" + username + ", userId=" + userId + ", uuId=" + uuId + "]";
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUuId() {
		System.out.println(uuId);
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
		System.out.println(uuId);
	}
	
	
	@Id
	private String username;
	private Long userId;
	private String uuId;
	
}
