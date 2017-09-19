package com.revature.smp.exceptions;

public class UsernameExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameExistsException() {	}

	public UsernameExistsException(String msg) {
		super(msg);
	}
}
