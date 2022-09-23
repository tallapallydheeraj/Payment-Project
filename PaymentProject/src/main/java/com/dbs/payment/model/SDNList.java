package com.dbs.payment.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="sdnlist")
public class SDNList {
	@Id
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SDNList() {
		// TODO Auto-generated constructor stub
	}
	
	public SDNList(String name) {
		//super();
		this.name = name;
	}
}
