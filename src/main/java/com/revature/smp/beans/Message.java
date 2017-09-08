package com.revature.smp.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Message implements Serializable {
	
	private static final long serialVersionUID = -8788118511720310200L;
	
	private String user;
	private long time;
	private String text;
	
	public Message() {
	}
	
	public Message(String user, long time, String text) {
		this.user = user;
		this.time = time;
		this.text = text;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
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
		return "Message [user=" + user + ", time=" + time + ", text=" + text + "]";
	}
	
}
