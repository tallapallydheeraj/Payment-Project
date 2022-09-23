package com.dbs.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	private String customerid;
	private String accountholdername;
	private String customeraddress;
	private String customercity;
	private byte overdraftflag;
	@OneToOne
	@JoinColumn(name="bic")
	private Bank bic;
	private double clearbalance;
	@Column(columnDefinition = "char(1)")
	private String customertype;
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getAccountholdername() {
		return accountholdername;
	}
	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}
	public String getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	public String getCustomercity() {
		return customercity;
	}
	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}
	public byte getOverdraftflag() {
		return overdraftflag;
	}
	public void setOverdraftflag(byte overdraftflag) {
		this.overdraftflag = overdraftflag;
	}
	public Bank getBic() {
		return bic;
	}
	public void setBic(Bank bic) {
		this.bic = bic;
	}
	public double getClearbalance() {
		return clearbalance;
	}
	public void setClearbalance(double clearbalance) {
		this.clearbalance = clearbalance;
	}
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Customer(String customerid, String accountholdername, String customeraddress, String customercity,
			byte overdraftflag, Bank bic, double clearbalance, String customertype) {
		super();
		this.customerid = customerid;
		this.accountholdername = accountholdername;
		this.customeraddress = customeraddress;
		this.customercity = customercity;
		this.overdraftflag = overdraftflag;
		this.bic = bic;
		this.clearbalance = clearbalance;
		this.customertype = customertype;
	}
	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", accountholdername=" + accountholdername + ", customeraddress="
				+ customeraddress + ", customercity=" + customercity + ", overdraftflag=" + overdraftflag + ", bic="
				+ bic + ", clearbalance=" + clearbalance + ", customertype=" + customertype + "]";
	}
	
}
