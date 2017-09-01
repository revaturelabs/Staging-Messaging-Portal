package com.revature.smp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="MESSAGE")
public class Message{
	
	@Id
	@Column(name="MESSAGE_BLOB_ID")
	private int messageBlobId;
	
	@Column(name="MESSAGE_BLOB")
	private byte[] messageBlob;
	
	@Column(name="MESSAGE_ROOM_ID")
	private int messageRoomId;

	public int getMessageBlobId() {
		return messageBlobId;
	}

	public void setMessageBlobId(int messageBlobId) {
		this.messageBlobId = messageBlobId;
	}

	public byte[] getMessageBlob() {
		return messageBlob;
	}

	public void setMessageBlob(byte[] messageBlob) {
		this.messageBlob = messageBlob;
	}

	public int getMessageRoomId() {
		return messageRoomId;
	}

	public void setMessageRoomId(int messageRoomId) {
		this.messageRoomId = messageRoomId;
	}

	@Override
	public String toString() {
		return "Message [messageBlobId=" + messageBlobId + ", messageBlob=" + messageBlob + ", messageRoomId="
				+ messageRoomId + "]";
	}
	
	

}
