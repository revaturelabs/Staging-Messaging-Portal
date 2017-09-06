package com.revature.smp.services.registration;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.smp.beans.User;
import com.revature.smp.repositories.UserRepository;

@Service
public class AssociateRegistrationService {
	
	@Autowired
	UserRepository userRepo;
	
	/**
	 * 
	 * @param request - contains First name, last name and email fields 
	 * @throws Exception
	 */
	public void registerAssociate(User userRequest) throws Exception{
		// TODO - username may need to be updated if duplicate already exists in DB
		userRequest.setUsername(this.generateUniqueUsername(userRequest));
		
		// Once username is determined, persist user to DB
		userRequest.setPassword(this.generateFirstTimePassword());
		userRepo.save(userRequest);
		
		// TODO - email user with registration instructions
		return;
	}
	
	/**
	 * 
	 * @param user
	 * @return username based on first name and last name, ensure duplicate does 
	 * not exist, if username does exist, apply numeric code to ensure uniqueness
	 */
	public String generateUniqueUsername(User user) {
		String fullName = user.getFirstName()+"."+user.getLastName();
		String uniqueUsername = fullName;
		int i = 1;
		
		while( userRepo.findByUsername(uniqueUsername) != null) {
			uniqueUsername = fullName + i;
			i++;
		}
		
		return uniqueUsername;
	}
	
	public String generateFirstTimePassword() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			     .withinRange('!', '~').build();
		String oneTimePassword = generator.generate(12);
		return oneTimePassword;
	}
	
	public void emailAssociateAccountSetup() {
		//TODO
	}

}
