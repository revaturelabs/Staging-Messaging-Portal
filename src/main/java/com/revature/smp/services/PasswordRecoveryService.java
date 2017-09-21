package com.revature.smp.services;

import com.revature.smp.beans.User;

public interface PasswordRecoveryService {
	
	void updateUserPassword(User user);
	String generateTemporaryPassword();
	User getRegisteredUser(String email);
	String encryptAndStorePassword(User user);
	String decryptPassword(User user);
	void emailAssociateTemporaryPassword();
	
}
