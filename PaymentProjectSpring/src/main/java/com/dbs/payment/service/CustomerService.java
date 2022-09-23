package com.dbs.payment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.Customer;
import com.dbs.payment.repo.CustomerRepository;
import com.dbs.payment.util.JwtUtil;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	//@Autowired
	//private JwtUtil jwtutil;
	
	public String insertCustomer(Customer customer) {
		if(this.repo.findById(customer.getCustomerid()).isPresent())
			return "not inserted";
		try {
			return this.repo.save(customer).getCustomerid();
		}
		catch(IllegalArgumentException iae) {
			return "not inserted";
		}
	}
	
	public boolean deleteCustomer(String id) throws Exception {
		try {
			this.repo.deleteById(id);
		}catch(IllegalArgumentException e )
		{
			
			throw new IllegalArgumentException("Could not delete");
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		return true;
			
		}
	
	public boolean updateCustomer(Customer customer) {
		try {
			this.repo.save(customer);
		}
		catch(IllegalArgumentException iae) {
			return false;
		}
		return true;
	}
	
	public Customer findById(String id) {
		try {
			Optional<Customer> c=this.repo.findById(id);
			return c.orElseThrow(()->{
				return new EntityNotFoundException("Product with "+id + " does not exist");
			});
			}
		catch(IllegalArgumentException iae) {
			return null;
		}
		}
	
	//public Customer findCurrentUser() {}
	
	public List<Customer> getCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		this.repo.findAll().forEach(cust->customers.add(cust));
		return customers;
	}
	
	public boolean sendMoney(Customer sender, String receiver, double money, double transferfee) {
		double total=money+transferfee;
		if(total>0.0) {
		if(sender.getClearbalance()>total) {
			sender.setClearbalance(sender.getClearbalance()-total);
			//updateCustomer(sender);
			return true;
		}
		else {
			if(sender.getOverdraftflag()==1 && sender.getClearbalance()>0.0) {
				sender.setClearbalance(sender.getClearbalance()-total);
			    //updateCustomer(sender);
				return true;
			}
		}
		}
		return false;
	}
	}
	

