package com.revature.smp.services.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.smp.beans.User;
import com.revature.smp.domain.UserRegistrationRequest;
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

}
