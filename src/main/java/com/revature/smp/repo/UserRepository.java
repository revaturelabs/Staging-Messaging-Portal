package com.revature.smp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByActive(String active);

	User findByUsername(String username);
	
}
