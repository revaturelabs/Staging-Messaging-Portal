package com.revature.smp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "MESSAGE")
public class MessageClob {
	
	@Id
	@Column(name = "MESSAGE_CLOB_ID")
	private int messageClobId;
	
	@Column(name = "MESSAGE_CLOB")
	@Lob
	private String messageClob;
	
	@Column(name = "MESSAGE_ROOM_ID")
	private int messageRoomId;
	
	public MessageClob() {
	}
	
	public MessageClob(int messageClobId, int messageRoomId) {
		this.messageClobId = messageClobId;
		this.messageClob = messageClob;
		this.messageRoomId = messageRoomId;
	}
	
	public MessageClob(int messageClobId, String messageClob,
			int messageRoomId) {
		this.messageClobId = messageClobId;
		this.messageClob = messageClob;
		this.messageRoomId = messageRoomId;
	}
	
	public int getMessageClobId() {
		return messageClobId;
	}
	
	public void setMessageClobId(int messageClobId) {
		this.messageClobId = messageClobId;
	}
	
	public String getMessageClob() {
		return messageClob;
	}
	
	public void setMessageClob(String messageClob) {
		this.messageClob = messageClob;
	}
	
	public int getMessageRoomId() {
		return messageRoomId;
	}
	
	public void setMessageRoomId(int messageRoomId) {
		this.messageRoomId = messageRoomId;
	}
	
	@Override
	public String toString() {
		return "Message [messageClobId="
				+ messageClobId
				+ ", messageClob="
				+ messageClob
				+ ", messageRoomId="
				+ messageRoomId
				+ "]";
	}
	
}
