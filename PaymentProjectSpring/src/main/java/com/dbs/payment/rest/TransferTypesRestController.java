package com.dbs.payment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.TransferTypes;
import com.dbs.payment.service.TransferTypesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transfertypes")
public class TransferTypesRestController {
	@Autowired
	private TransferTypesService service;
	@GetMapping
	public List<TransferTypes> getAllTransferTypes(){
		return this.service.getAllTransferTypes();
	}
}
