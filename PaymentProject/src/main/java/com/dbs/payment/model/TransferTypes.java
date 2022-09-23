package com.dbs.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transfertypes")
public class TransferTypes {
	@Id
	@Column(columnDefinition = "CHAR(1)")
	private String transfertypecode;
	private String transfertypedescription;
	@Override
	public String toString() {
		return "TransferTypes [transfertypecode=" + transfertypecode + ", transfertypedescription="
				+ transfertypedescription + "]";
	}
	public TransferTypes() {
		// TODO Auto-generated constructor stub
	}
	public TransferTypes(String transfertypecode, String transfertypedescription) {
		//super();
		this.transfertypecode = transfertypecode;
		this.transfertypedescription = transfertypedescription;
	}
	public String getTransfertypecode() {
		return transfertypecode;
	}
	public void setTransfertypecode(String transfertypecode) {
		this.transfertypecode = transfertypecode;
	}
	public String getTransfertypedescription() {
		return transfertypedescription;
	}
	public void setTransfertypedescription(String transfertypedescription) {
		this.transfertypedescription = transfertypedescription;
	}
}
