package com.revature.smp.Security;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.revature.smp.beans.User;

public interface UserQuery extends Repository <User, String> {

	@Query("SELECT username FROM user_table WHERE username = Username ")
	String getUserByUsername(@Param("Username") String Username);
	
	@Query("SELECT password FROM user_table WHERE password = Password ")
	String getPasswordByPassword(@Param("Password") String Username);
}
