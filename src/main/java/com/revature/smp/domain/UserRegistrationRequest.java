package com.revature.smp.domain;

public class UserRegistrationRequest {
	
	public UserRegistrationRequest() {
		super();
	}

	@Override
	public String toString() {
		return "UserRegistrationRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	private String firstName;
	private String lastName;
	private String email;
	private int locationId;
	
	public UserRegistrationRequest(String firstName, String lastName, String email, int locationId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.setLocationId(locationId);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

}
