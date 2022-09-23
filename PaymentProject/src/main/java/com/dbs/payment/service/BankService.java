package com.dbs.payment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.Bank;
import com.dbs.payment.repo.BankRepository;

@Service
public class BankService {
	@Autowired
	private BankRepository repo;
	
	public String insertBank(Bank bank) {
		if(this.repo.findById(bank.getBic()).isPresent())
			return "not inserted";
		try {
			return this.repo.save(bank).getBic();
		}
		catch(IllegalArgumentException iae) {
			return "not inserted";
		}
	}
	
	public boolean deleteBank(String id) throws Exception {
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
	
	public boolean updateBank(Bank bank) {
		try {
			this.repo.save(bank);
		}
		catch(IllegalArgumentException iae) {
			return false;
		}
		return true;
	}
	
	public Bank findById(String id) {
		try {
			Optional<Bank> c=this.repo.findById(id);
			return c.orElseThrow(()->{
				return new EntityNotFoundException("Bank with "+id + " does not exist");
			});
			}
		catch(IllegalArgumentException iae) {
			return null;
		}
		}
	
	public List<Bank> getBanks(){
		List<Bank> banks = new ArrayList<Bank>();
		this.repo.findAll().forEach(bank->banks.add(bank));
		return banks;
	}
	}
