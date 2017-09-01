package com.revature.smp.dao;

import org.springframework.stereotype.Repository;

import com.revature.smp.beans.Message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageDAO extends JpaRepository<Message,Integer>{
	Message findByMessageBlobId(int messageBlobId);
	Page<Message> findByMessageRoomIdOrderByMessageBlobIdDesc(int messageRoomId, Pageable p);
	Page<Message> findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(int messageRoomId,int messageBlobId, Pageable p);
	
	default Message getMostRecent(int messageRoomId) {
		Page<Message> p = findByMessageRoomIdOrderByMessageBlobIdDesc(messageRoomId, new PageRequest(0,1));
		if(p.hasContent()) {
			return p.getContent().get(0);
		}else {
			return null;
		}
	}
	
	default Message getPrevious(int messageRoomId, int messageBlobId) {
		Page<Message> p = findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc
				(messageRoomId,messageBlobId,new PageRequest(0,1));
		if(p.hasContent()) {
			return p.getContent().get(0);
		}else {
			return null;
		}
	}
}
