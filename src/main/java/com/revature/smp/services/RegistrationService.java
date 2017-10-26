package com.revature.smp.services;

import java.util.List;

import com.revature.smp.beans.User;
import com.revature.smp.exceptions.UsernameExistsException;

public interface RegistrationService {

	void registerAssociate(User userRequest) throws UsernameExistsException;
	
	String generateUniqueUsername(User user);
	
	String generateFirstTimePassword();
	
	void emailAssociateAccountSetup();
	
	List<User> getRegisteringUsers();
	
	void onboardUser(User user);
	
	String encryptAndStorePassword(User user);
	
	String decryptPassword(User user);
}
