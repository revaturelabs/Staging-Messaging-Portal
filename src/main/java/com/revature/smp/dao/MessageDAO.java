package com.revature.smp.dao;

import org.springframework.stereotype.Repository;

import com.revature.smp.beans.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageDAO extends JpaRepository<Message,Integer>{
	Message findByMessageBlobId(int messageBlobId);
	List<Message> findByMessageRoomIdOrderByMessageBlobIdDesc(int messageRoomId);
	List<Message> findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(int messageRoomId,int messageBlobId);
}
