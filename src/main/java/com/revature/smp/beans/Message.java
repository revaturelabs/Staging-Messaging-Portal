package com.revature.smp.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="MESSAGE_TABLE")
public class Message implements Serializable {
	
	private static final long serialVersionUID = -8788118511720310200L;
	
	@Id
	@SequenceGenerator(name="SEQ_MSG", sequenceName="SEQ_MESSAGE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MSG")
	@Column(name="message_id")
	private int messageId;
	
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="message_time")
	private Date time;
	
	@Column(name="message_text")
	private String text;
	
	public Message() {
		
	}

	public Message(int messageId, int roomId, String username, Date time, String text) 
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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
