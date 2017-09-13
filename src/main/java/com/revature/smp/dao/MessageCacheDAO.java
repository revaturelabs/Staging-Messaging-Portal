package com.revature.smp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.smp.beans.MessageCache;

public interface MessageCacheDAO extends JpaRepository<MessageCache, Integer> {

	List<MessageCache> findByRoomIdOrderByCreatedDesc(int roomId);
	
	default List<MessageCache> getPrevious(int roomId) {
		return findByRoomIdOrderByCreatedDesc(roomId);
	}
}
