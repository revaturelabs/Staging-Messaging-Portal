package com.revature.smp.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class MessageBean implements Serializable {
	
	private static final long serialVersionUID = -6407809690643413873L;
	
	private int msgId;
	
	private String message;
	
	public MessageBean() {
	}
	
	public MessageBean(int msgId, String message) {
		this.msgId = msgId;
		this.message = message;
	}
	
	public int getMsgId() {
		return msgId;
	}
	
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "MessageBean [msgId=" + msgId + ", message=" + message + "]";
	}
	
}
