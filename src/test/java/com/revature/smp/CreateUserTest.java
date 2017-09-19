package com.revature.smp;

import org.junit.Test;

import com.revature.smp.beans.User;

public class CreateUserTest {

	@Test
	public void test() {
		User completeUser = new User("Luka", "Sancha", "lms@reva.zip", 1);
		System.out.println(completeUser);
	}

}
