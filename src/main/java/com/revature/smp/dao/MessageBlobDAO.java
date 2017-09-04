package com.revature.smp.dao;

import org.springframework.stereotype.Repository;

import com.revature.smp.beans.MessageBlob;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageBlobDAO extends JpaRepository<MessageBlob,Integer>{
	MessageBlob findByMessageBlobId(int messageBlobId);
	Page<MessageBlob> findByMessageRoomIdOrderByMessageBlobIdDesc(int messageRoomId, Pageable p);
	Page<MessageBlob> findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(int messageRoomId,int messageBlobId, Pageable p);
	List<MessageBlob> findByMessageRoomIdAndMessageBlobIdGreaterThanEqualOrderByMessageBlobIdAsc(int messageRoomId,int messageBlobId);
	
	default List<MessageBlob> getMostRecent(int messageRoomId) {
		Page<MessageBlob> p = findByMessageRoomIdOrderByMessageBlobIdDesc(messageRoomId, new PageRequest(0,1));
		return p.getContent();
	}
	
	default List<MessageBlob> getPrevious(int messageRoomId, int messageBlobId) {
		Page<MessageBlob> p = findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc
				(messageRoomId,messageBlobId,new PageRequest(0,1));
		return p.getContent();
	}
	
	default List<MessageBlob> getUpdate(int messageRoomId, int messageBlobId){
		List<MessageBlob> l = findByMessageRoomIdAndMessageBlobIdGreaterThanEqualOrderByMessageBlobIdAsc
				(messageRoomId,messageBlobId);
		return l;
	}
}
