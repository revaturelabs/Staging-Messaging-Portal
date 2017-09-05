package com.revature.smp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.User;

/**
 * The Interface UserDAO.
 */
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	
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
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<User> findAll();
	
}
