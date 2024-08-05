package com.JithendraProject.Employee.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JithendraProject.Employee.Entity.CurrentUserSession;

@Repository
public interface SessionRepo  extends JpaRepository<CurrentUserSession, String> {
	public Optional<CurrentUserSession> findByUuId(String uuId);
}
