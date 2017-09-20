package com.revature.smp.beans;

import java.io.Serializable;

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
	
	public MessageRoom() {
		
	}

	public MessageRoom(int roomId, String roomName) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
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

	@Override
	public String toString() {
		return "MessageRoom [roomId=" + roomId + ", roomName=" + roomName + "]";
	}
	
}
