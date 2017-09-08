package com.revature.smp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.smp.beans.MessageClob;

@Repository
public interface MessageClobDAO extends JpaRepository<MessageClob, Integer> {
	MessageClob findByMessageClobId(int messageClobId);
	
	Page<MessageClob> findByMessageRoomIdOrderByMessageClobIdDesc(
			int messageRoomId, Pageable p);
	
	Page<MessageClob> findByMessageRoomIdAndMessageClobIdLessThanOrderByMessageClobIdDesc(
			int messageRoomId, int messageClobId, Pageable p);
	
	List<MessageClob> findByMessageRoomIdAndMessageClobIdGreaterThanEqualOrderByMessageClobIdAsc(
			int messageRoomId, int messageClobId);
	
	default List<MessageClob> getMostRecent(int messageRoomId) {
		return findByMessageRoomIdOrderByMessageClobIdDesc(messageRoomId,
				new PageRequest(0, 1)).getContent();
	}
	
	default List<MessageClob> getPrevious(int messageRoomId,
			int messageClobId) {
		return findByMessageRoomIdAndMessageClobIdLessThanOrderByMessageClobIdDesc(
				messageRoomId, messageClobId, new PageRequest(0, 1))
						.getContent();
	}
	
	default List<MessageClob> getUpdate(int messageRoomId, int messageClobId) {
		List<MessageClob> l =
				findByMessageRoomIdAndMessageClobIdGreaterThanEqualOrderByMessageClobIdAsc(
						messageRoomId, messageClobId);
		return l;
	}
}
