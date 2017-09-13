package com.revature.smp.services;

import java.util.List;

import com.revature.smp.beans.User;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Find user by email.
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */
	public User findUserByEmail(String email);
	
	/**
	 * Find user by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the user
	 */
	public User findUserByUserId(int userId);
	
	/**
	 * Find users by role.
	 *
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findUsersByRole(String role);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<User> findAll();
	
	/**
	 * Creates the user.
	 *
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean createUser(User user);
	
	/**
	 * Update user.
	 *
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean updateUser(User user);
	
	/**
	 * Deactivate user.
	 *
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean deactivateUser(User user);
	
}
