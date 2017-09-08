package com.revature.smp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="MESSAGE")
public class MessageClob {
	
	@Id
	@Column(name="MESSAGE_CACHE_ID")
	private int messageCacheId;
	
	@Column(name="MESSAGE_CLOB")
	@Lob
	private String messageClob;
	
	@Column(name="CREATED")
	private long created;
	
	public MessageClob() {
		
	}

	public MessageClob(int messageCacheId, String messageClob, long created) 
	{
		super();
		this.messageCacheId = messageCacheId;
		this.messageClob = messageClob;
		this.created = created;
	}

	public int getMessageCacheId() {
		return messageCacheId;
	}

	public void setMessageCacheId(int messageCacheId) {
		this.messageCacheId = messageCacheId;
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
		return "MessageClob [messageCacheId=" + messageCacheId + ", messageClob=" + messageClob + ", created=" + created
				+ "]";
	}
	
}
