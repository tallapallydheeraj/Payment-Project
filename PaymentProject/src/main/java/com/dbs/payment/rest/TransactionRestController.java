package com.dbs.payment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.ResponsePage;
import com.dbs.payment.model.Transaction;
import com.dbs.payment.service.SDNService;
import com.dbs.payment.service.TransactionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")
public class TransactionRestController {
	@Autowired
	private TransactionService service;
	//@Autowired
	//private SDNService sdnservice;
	@GetMapping("/{id}")
	public List<Transaction> getAllByCustomerId(@PathVariable String id){
		return this.service.getAllBycustomerId(id);
	}
	@PostMapping
	public ResponseEntity<ResponsePage> postTransaction(@RequestBody Transaction tran){
		int id = this.service.postTransaction(tran);
		if(id==-3)
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(new ResponsePage(-3, "Receiver Name is in Blocked List"));
		if(id == -2)
			return ResponseEntity
					.status(HttpStatus.PROCESSING)
					.body(new ResponsePage(-2, "Processing"));
		else if(id == -1)
			return  ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(new ResponsePage(-1,"Invalid Transaction"));
		else
			return  ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(new ResponsePage(1,"Transaction inserted with id "+id));
		
	}
	
	
}
