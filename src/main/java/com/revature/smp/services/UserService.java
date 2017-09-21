package com.revature.smp.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.smp.beans.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public User getByUsername(String username) throws SQLException;
	
	public List<User> getByActiveStatus(String active);
	
}
