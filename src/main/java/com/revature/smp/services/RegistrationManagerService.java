package com.revature.smp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.smp.beans.User;
import com.revature.smp.repo.UserRepository;

@Service
public class RegistrationManagerService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getRegisteringUsers(){
		return userRepo.findByActive("0");
	}
	
	public void onboardUser(User user) {
		
	}
	
	public String encryptAndStorePassword(User user) {
		return null;
		
	}
	
	public String decryptPassword(User user) {
		return null;
		
	}

}
