package com.revature.smp.dao;

import org.springframework.stereotype.Repository;

import com.revature.smp.beans.MessageBlob;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageBlobDAO extends JpaRepository<MessageBlob,Integer>{
	MessageBlob findByMessageBlobId(int messageBlobId);
	Page<MessageBlob> findByMessageRoomIdOrderByMessageBlobIdDesc(int messageRoomId, Pageable p);
	Page<MessageBlob> findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(int messageRoomId,int messageBlobId, Pageable p);
	
	default MessageBlob getMostRecent(int messageRoomId) {
		Page<MessageBlob> p = findByMessageRoomIdOrderByMessageBlobIdDesc(messageRoomId, new PageRequest(0,1));
		if(p.hasContent()) {
			return p.getContent().get(0);
		}else {
			return null;
		}
	}
	
	default MessageBlob getPrevious(int messageRoomId, int messageBlobId) {
		Page<MessageBlob> p = findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc
				(messageRoomId,messageBlobId,new PageRequest(0,1));
		if(p.hasContent()) {
			return p.getContent().get(0);
		}else {
			return null;
		}
	}
}
