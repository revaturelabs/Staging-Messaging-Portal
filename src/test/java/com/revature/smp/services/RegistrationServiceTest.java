package com.revature.smp.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.smp.services.registration.AssociateRegistrationService;

public class RegistrationServiceTest {

	@Test
	public void testPasswordgeneration() {
		System.out.println(AssociateRegistrationService.generateFirstTimePassword());
	}

}
