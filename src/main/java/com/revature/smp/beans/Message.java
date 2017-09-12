package com.revature.smp.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="MESSAGE_TABLE")
public class Message implements Serializable {
	
	private static final long serialVersionUID = -8788118511720310200L;
	
	@Id
	@Column(name="message_id")
	private int messageId;
	
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="message_time")
	private long time;
	
	@Column(name="message_text")
	private String text;
	
	public Message() {
		
	}

	public Message(int messageId, int roomId, String username, long time, String text) 
	{
		super();
		this.messageId = messageId;
		this.roomId = roomId;
		this.username = username;
		this.time = time;
		this.text = text;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", roomId=" + roomId + ", username=" + username + ", time=" + time
				+ ", text=" + text + "]";
	}
	
}
