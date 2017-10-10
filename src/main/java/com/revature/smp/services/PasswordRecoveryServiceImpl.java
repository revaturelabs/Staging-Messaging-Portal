package com.revature.smp.services;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.smp.beans.User;

public class PasswordRecoveryServiceImpl implements PasswordRecoveryService{
	
	@Autowired
	UserServiceImpl userSvc;
	
	@Override
	public String generateTemporaryPassword() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			     .withinRange('!', '~').build();
		String oneTimePassword = generator.generate(12);
		return oneTimePassword;
	}

	@Override
	public User getRegisteredUser(String email) {
		return userSvc.getByEmail(email);
		
	}

	@Override
	public String encryptAndStorePassword(User user) {
		return null;
	}

	@Override
	public String decryptPassword(User user) {
		return null;
	}

	@Override
	public void emailAssociateTemporaryPassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserPassword(User user) {
		userSvc.saveUser(user);
	}

}
