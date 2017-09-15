package com.revature.smp.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="MESSAGE_CACHE")
public class MessageCache implements Serializable {
	
	private static final long serialVersionUID = 1947353860067803654L;

	@Id
	@Column(name="cache_id")
	@SequenceGenerator(name="SEQ_CACHE", sequenceName="SEQ_CACHE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CACHE")
	private int cacheId;
	
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="message_clob")
	@Lob
	private String messageClob;
	
	@Column(name="created")
	private long created;
	
	public MessageCache() { } // empty constructor

	public MessageCache(int cacheId, int roomId, String messageClob, long created) 
	{
		super();
		this.cacheId = cacheId;
		this.roomId = roomId;
		this.messageClob = messageClob;
		this.created = created;
	}

	public int getCacheId() {
		return cacheId;
	}

	public void setCacheId(int cacheId) {
		this.cacheId = cacheId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getMessageClob() {
		return messageClob;
	}

	public void setMessageClob(String messageClob) {
		this.messageClob = messageClob;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "MessageCache [cacheId=" + cacheId + ", roomId=" + roomId + ", messageClob=" + messageClob + ", created="
				+ created + "]";
	}

}
