package com.dbs.payment.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Bank;
import com.dbs.payment.service.BankService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bank")
public class BankRestController {
	@Autowired
	private BankService service;
	@GetMapping("/{bic}")
	public ResponseEntity<Object> getBank(@PathVariable String bic){
		try {
			Bank b=this.service.findById(bic);
			return ResponseEntity.status(HttpStatus.OK)
					.body(b);
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("bank not found");
		}
	}
}
