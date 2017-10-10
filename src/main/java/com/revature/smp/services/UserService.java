package com.revature.smp.services;

import java.util.List;

import com.revature.smp.beans.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User getByEmail(String email);
	
	public User getByUsername(String username);
	
	public List<User> getByActiveStatus(String active);
	
}
