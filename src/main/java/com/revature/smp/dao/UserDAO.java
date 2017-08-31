package com.revature.smp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	public User findUserByEmail(String email);
	public User findUserByUserId(int userId);
	public List<User> findUsersByRole(String role);
	public List<User> findAll();
	
}
