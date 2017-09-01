package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.User;


public interface UserService {
	
	public User findUserByEmail(String email);
	public User findUserByUserId(int userId);
	public List<User> findUsersByRole(String role);
	public List<User> findAll();
	public boolean createUser(User user);
	public boolean updateUser(User user);
	public boolean deactivateUser(User user);
	
}
