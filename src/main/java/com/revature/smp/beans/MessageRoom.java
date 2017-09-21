package com.revature.smp.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="MESSAGE_ROOM")
public class MessageRoom implements Serializable {

	private static final long serialVersionUID = -2944283440032776431L;

	@Id
	@SequenceGenerator(name="SEQ_MESSAGEROOM", sequenceName="SEQ_MSGROOM", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MESSAGEROOM")
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="room_name")
	private String roomName;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Message.class)
	@JoinColumn(name="room_id")
	private List<Message> messages;
	
	public MessageRoom() {
		
	}

	public MessageRoom(int roomId, String roomName, List<Message> messages) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.messages = messages;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "MessageRoom [roomId=" + roomId + ", roomName=" + roomName + ", messages=" + messages + "]";
	}

}
