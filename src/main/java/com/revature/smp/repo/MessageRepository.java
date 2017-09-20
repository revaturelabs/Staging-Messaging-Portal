package com.revature.smp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	//@Query("SELECT t.title FROM Todo t where t.id = :id") 
    //Optional<String> findTitleById(@Param("id") Long id);
	//List<Message> findById(int roomId);

}
