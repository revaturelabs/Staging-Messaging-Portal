package com.revature.smp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.smp.beans.User;
import com.revature.smp.exceptions.UsernameExistsException;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	UserService userSvc;
	
	/**
	 * Checks if the provided email is already in the database.
	 * Creates a new user with a random new password and pseudo-random username.
	 * @param request - contains First name, location, last name and email fields 
	 * @throws UsernameExistsException
	 */
	@Override
	public void registerAssociate(User userRequest) throws UsernameExistsException{
		User userInDatabase = userSvc.getByEmail(userRequest.getEmail());
		if(userInDatabase != null)
		{
			throw new UsernameExistsException("That email is already in use.");
		}
		userRequest.setUsername(this.generateUniqueUsername(userRequest));
		
		// Once username is determined, persist user to DB
		userRequest.setPassword(this.generateFirstTimePassword());
		userSvc.saveUser(userRequest);
		
		// TODO - email user with registration instructions
		return;
	}
	
	/**
	 * 
	 * @param user
	 * @return username based on first name and last name, ensure duplicate does 
	 * not exist, if username does exist, apply numeric code to ensure uniqueness
	 */
	@Override
	public String generateUniqueUsername(User user) {
		String fullName = user.getFirstName()+"."+user.getLastName();
		String uniqueUsername = fullName;
		int i = 1;
		
		while( userSvc.getByUsername(uniqueUsername) != null) 
		{
			uniqueUsername = fullName + i;
			i++;
		}
		
		return uniqueUsername;
	}
	
	@Override
	public String generateFirstTimePassword() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			     .withinRange('!', '~').build();
		String oneTimePassword = generator.generate(12);
		return oneTimePassword;
	}
	
	@Override
	public void emailAssociateAccountSetup() {
		//TODO
	}
	
	@Override
	public List<User> getRegisteringUsers() {
		return userSvc.getByActiveStatus("n");
	}
	
	@Override
	public void onboardUser(User user) {
		
	}
	
	@Override
	public String encryptAndStorePassword(User user) {
		return null;
		
	}
	
	@Override
	public String decryptPassword(User user) {
		return null;
		
	}

}
