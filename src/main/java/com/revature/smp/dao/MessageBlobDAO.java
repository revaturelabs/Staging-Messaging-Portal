package com.revature.smp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.MessageClob;

@Repository
public interface MessageBlobDAO extends JpaRepository<MessageClob, Integer> {
	MessageClob findByMessageBlobId(int messageBlobId);
	
	Page<MessageClob> findByMessageRoomIdOrderByMessageBlobIdDesc(
			int messageRoomId, Pageable p);
	
	Page<MessageClob> findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(
			int messageRoomId, int messageBlobId, Pageable p);
	
	List<MessageClob> findByMessageRoomIdAndMessageBlobIdGreaterThanEqualOrderByMessageBlobIdAsc(
			int messageRoomId, int messageBlobId);
	
	default List<MessageClob> getMostRecent(int messageRoomId) {
		return findByMessageRoomIdOrderByMessageBlobIdDesc(messageRoomId,
				new PageRequest(0, 1)).getContent();
	}
	
	default List<MessageClob> getPrevious(int messageRoomId,
			int messageBlobId) {
		return findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(
				messageRoomId, messageBlobId, new PageRequest(0, 1))
						.getContent();
	}
	
	default List<MessageClob> getUpdate(int messageRoomId, int messageBlobId) {
		List<MessageClob> l =
				findByMessageRoomIdAndMessageBlobIdGreaterThanEqualOrderByMessageBlobIdAsc(
						messageRoomId, messageBlobId);
		return l;
	}
}
