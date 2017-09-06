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
		userRepo.save(userRequest);
		return;
	}
	
	public static String generateFirstTimePassword() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			     .withinRange('!', '~').build();
		String oneTimePassword = generator.generate(12);
		return oneTimePassword;
	}
	
	public void emailAssociateAccountSetup() {
		//todo
	}

}
