package com.revature.smp.domain;

public class PasswordRecoveryRequest {

	public PasswordRecoveryRequest() {
		super();
	}
	
	private String email;
	
	public PasswordRecoveryRequest(String email) {
		this.setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
