package com.revature.smp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.Message;

@Repository
public interface MessageDAO extends JpaRepository<Message, Integer> {
	
	List<Message> findByRoomIdOrderByTime(int roomId);
	
	default List<Message> getUpdate(int roomId) {
		return findByRoomIdOrderByTime(roomId);
	}
	
}
