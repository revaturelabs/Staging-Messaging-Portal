package com.revature.smp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.smp.beans.User;
import com.revature.smp.repo.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	@Override
	public User getByUsername(String username)
	{		
		User user = userRepo.findByUsername(username);		
		if (user != null)
		{
			return user;
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<User> getByActiveStatus(String active) {
		return userRepo.findByActive(active);
	}

	@Override
	public User getByEmail(String email) {
		return userRepo.findByEmail(email);
	}	
}