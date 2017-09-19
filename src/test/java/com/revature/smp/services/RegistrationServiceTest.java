package com.revature.smp.services;

import org.junit.Test;

public class RegistrationServiceTest {
	

	@Test
	public void testPasswordgeneration() {
		AssociateRegistrationService service = new AssociateRegistrationService();
		System.out.println(service.generateFirstTimePassword());
	}

}
