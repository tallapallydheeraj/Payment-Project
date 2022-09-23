package com.dbs.payment.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbs.payment.model.Transaction;
import com.dbs.payment.repo.CustomerRepository;
import com.dbs.payment.repo.MessageRepository;
import com.dbs.payment.repo.TransactionRepository;


@Service
public class TransactionService {
	@Autowired
	private CustomerRepository crepo;
	@Autowired
	private TransactionRepository repo;
	@Autowired
	private CustomerService service;
	@Autowired
	private MessageRepository mrepo;
	@Autowired
	private SDNService sdnservice;
	
	public int postTransaction(Transaction tran) {
		
			/*if(this.repo.findById(tran.getTransactionid()).isPresent())
				return -1;*/
		String receivername = tran.getReceiveraccountholdername().toLowerCase()
				.replaceAll("[\\s,.-]", "");
				
		List<String> l= sdnservice.findAll();
		if(l.contains(receivername))
			return -3;
		tran.setMessage(mrepo.findById(tran.
				getMessage().getMessagecode()).orElseThrow());
		tran.setCustomer(crepo.findById(tran.
				getCustomer().getCustomerid()).orElseThrow());
		tran.settransferdate(LocalDate.now());
			
		if(this.service.sendMoney(tran.getCustomer(), tran.getreceiveraccountholdernumber(), tran.getInramount(), tran.getTransferfees())) {
			try {
				return this.repo.save(tran).getTransactionid();
			}catch(IllegalArgumentException e )
			{
				return -2;//transaction not saved 
			}
			}
			else {
				return -1;//invalid transaction
			}
		
	}
	public List<Transaction> getAllBycustomerId(String id){
		return this.repo.findAllByCustomerCustomerid(id);
	}
}
