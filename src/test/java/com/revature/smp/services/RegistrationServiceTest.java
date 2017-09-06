package com.revature.smp.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.smp.services.registration.AssociateRegistrationService;

public class RegistrationServiceTest {
	

	@Test
	public void testPasswordgeneration() {
		AssociateRegistrationService service = new AssociateRegistrationService();
		System.out.println(service.generateFirstTimePassword());
	}

}
