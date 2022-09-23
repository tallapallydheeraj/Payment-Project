package com.dbs.payment.model;

 

import java.time.LocalDate;

 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 

@Entity
@Table(name="transaction")
public class Transaction {
    
    @Id
    private int transactionid;
    
    @ManyToOne
    @JoinColumn(name="customerid")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name="currencycode")
    private Currency currency;
    
    @ManyToOne
    @JoinColumn(name="senderBIC")
    private Bank senderBIC;
    
    @ManyToOne
    @JoinColumn(name="receiverBIC")
    private Bank receiverBIC;
    
    //@Column(columnDefinition = "char(11)")
    private String receiveraccountholdernumber;
    
    
    private String receiveraccountholdername;
    
    @ManyToOne
    @JoinColumn(name="transfertypecode")
    private TransferTypes transfertypecode;
    
    @ManyToOne
    @JoinColumn(name="messagecode")
    private Message message;
    
    
    private double currencyamount;
    
    private double transferfees;
    
    private double inramount;
    
    private LocalDate transferdate;
    
    
    
    public Transaction() {
        
    }

    
    public Transaction(int transactionid, Customer customer, Currency currency, Bank senderBIC, Bank receiverBIC,
            String receiveraccountholdernumber, String receiveraccountholdername, TransferTypes transfertypecode,
            Message message, double currencyamount, double transferfees, double inramount, LocalDate transferdate) {
        super();
        this.transactionid = transactionid;
        this.customer = customer;
        this.currency = currency;
        this.senderBIC = senderBIC;
        this.receiverBIC = receiverBIC;
        this.receiveraccountholdernumber = receiveraccountholdernumber;
        this.receiveraccountholdername = receiveraccountholdername;
        this.transfertypecode = transfertypecode;
        this.message = message;
        this.currencyamount = currencyamount;
        this.transferfees = transferfees;
        this.inramount = inramount;
        this.transferdate = transferdate;
    }

 

 

    public int getTransactionid() {
        return transactionid;
    }

 

 

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

 

 

    public Customer getCustomer() {
        return customer;
    }

 

 

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

 

 

    public Currency getCurrency() {
        return currency;
    }

 

 

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

 

 

    public Bank getSenderBIC() {
        return senderBIC;
    }

 

 

    public void setSenderBIC(Bank senderBIC) {
        this.senderBIC = senderBIC;
    }

 

 

    public Bank getReceiverBIC() {
        return receiverBIC;
    }

 

 

    public void setReceiverBIC(Bank receiverBIC) {
        this.receiverBIC = receiverBIC;
    }

 

 

    public String getreceiveraccountholdernumber() {
        return receiveraccountholdernumber;
    }

 

 

    public void setreceiveraccountholdernumber(String receiveraccountholdernumber) {
        this.receiveraccountholdernumber = receiveraccountholdernumber;
    }

 

 

    public String getReceiveraccountholdername() {
        return receiveraccountholdername;
    }

 

 

    public void setReceiveraccountholdername(String receiveraccountholdername) {
        this.receiveraccountholdername = receiveraccountholdername;
    }

 

 

    public TransferTypes getTransfertypecode() {
        return transfertypecode;
    }

 

 

    public void setTransfertypecode(TransferTypes transfertypecode) {
        this.transfertypecode = transfertypecode;
    }

 

 

    public Message getMessage() {
        return message;
    }

 

 

    public void setMessage(Message message) {
        this.message = message;
    }

 

 

    public double getCurrencyamount() {
        return currencyamount;
    }

 

 

    public void setCurrencyamount(double currencyamount) {
        this.currencyamount = currencyamount;
    }

 

 

    public double getTransferfees() {
        return transferfees;
    }

 

 

    public void setTransferfees(double transferfees) {
        this.transferfees = transferfees;
    }

 

 

    public double getInramount() {
        return inramount;
    }

 

 

    public void setInramount(double inramount) {
        this.inramount = inramount;
    }

 

 

    public LocalDate gettransferdate() {
        return transferdate;
    }

 

 

    public void settransferdate(LocalDate transferdate) {
        this.transferdate = transferdate;
    }

 

 

    @Override
    public String toString() {
        return "Transaction [transactionid=" + transactionid + ", customer=" + customer + ", currency=" + currency
                + ", senderBIC=" + senderBIC + ", receiverBIC=" + receiverBIC + ", receiveraccountholdernumber="
                + receiveraccountholdernumber + ", receiveraccountholdername=" + receiveraccountholdername
                + ", transfertypecode=" + transfertypecode + ", message=" + message + ", currencyamount="
                + currencyamount + ", transferfees=" + transferfees + ", inramount=" + inramount + ", transferdate="
                + transferdate + "]";
    }

 

    
}