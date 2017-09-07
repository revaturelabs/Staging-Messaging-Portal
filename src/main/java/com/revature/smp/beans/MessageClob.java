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
	private int messageBlobId;
	
	@Column(name = "MESSAGE_CLOB")
	@Lob
	private String messageClob;
	
	@Column(name = "MESSAGE_ROOM_ID")
	private int messageRoomId;
	
	public MessageClob() {
	}
	
	public MessageClob(int messageBlobId, int messageRoomId) {
		this.messageBlobId = messageBlobId;
		this.messageClob = messageClob;
		this.messageRoomId = messageRoomId;
	}
	
	public MessageClob(int messageBlobId, String messageClob,
			int messageRoomId) {
		this.messageBlobId = messageBlobId;
		this.messageClob = messageClob;
		this.messageRoomId = messageRoomId;
	}
	
	public int getMessageBlobId() {
		return messageBlobId;
	}
	
	public void setMessageBlobId(int messageClobId) {
		this.messageBlobId = messageClobId;
	}
	
	public String getMessageBlob() {
		return messageClob;
	}
	
	public void setMessageBlob(String messageClob) {
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
		return "Message [messageBlobId="
				+ messageBlobId
				+ ", messageBlob="
				+ messageClob
				+ ", messageRoomId="
				+ messageRoomId
				+ "]";
	}
	
}
