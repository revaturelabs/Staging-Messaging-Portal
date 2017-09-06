package com.revature.smp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.User;
import java.lang.String;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findByActive(String active);
	public User findByUsername(String username);
	
}
