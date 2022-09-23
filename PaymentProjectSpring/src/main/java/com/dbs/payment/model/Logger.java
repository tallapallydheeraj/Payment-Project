package com.dbs.payment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Logger {
	@Id
	private int loggerid;
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="userid")
	private CustomerUser customeruser;
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;
	private String screename;
	private String action;
	private String ipaddress;
	public Logger() {
		// TODO Auto-generated constructor stub
	}
	public Logger(int loggerid, Customer customer, CustomerUser customeruser, Employee employee, String screename,
			String action, String ipaddress) {
		super();
		this.loggerid = loggerid;
		this.customer = customer;
		this.customeruser = customeruser;
		this.employee = employee;
		this.screename = screename;
		this.action = action;
		this.ipaddress = ipaddress;
	}
	@Override
	public String toString() {
		return "Logger [loggerid=" + loggerid + ", customer=" + customer + ", customeruser=" + customeruser
				+ ", employee=" + employee + ", screename=" + screename + ", action=" + action + ", ipaddress="
				+ ipaddress + "]";
	}
	public int getLoggerid() {
		return loggerid;
	}
	public void setLoggerid(int loggerid) {
		this.loggerid = loggerid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CustomerUser getCustomeruser() {
		return customeruser;
	}
	public void setCustomeruser(CustomerUser customeruser) {
		this.customeruser = customeruser;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getScreename() {
		return screename;
	}
	public void setScreename(String screename) {
		this.screename = screename;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
