package com.revature.smp.services;

import org.junit.Test;

public class RegistrationServiceImplTest {
	

	@Test
	public void testPasswordgeneration() {
		RegistrationServiceImpl service = new RegistrationServiceImpl();
		System.out.println(service.generateFirstTimePassword());
	}

}
