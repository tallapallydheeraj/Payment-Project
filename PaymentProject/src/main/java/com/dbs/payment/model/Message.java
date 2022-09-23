package com.dbs.payment.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	private String messagecode;
	private String instruction;
	@Override
	public String toString() {
		return "Message [messagecode=" + messagecode + ", instruction=" + instruction + "]";
	}
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Message(String messagecode, String instruction) {
		super();
		this.messagecode = messagecode;
		this.instruction = instruction;
	}
	public String getMessagecode() {
		return messagecode;
	}
	public void setMessagecode(String messagecode) {
		this.messagecode = messagecode;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
}
