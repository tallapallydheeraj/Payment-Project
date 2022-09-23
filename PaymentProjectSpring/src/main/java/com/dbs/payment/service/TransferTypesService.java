package com.dbs.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.TransferTypes;
import com.dbs.payment.repo.TransferTypesRepository;

@Service
public class TransferTypesService {
	@Autowired
	private TransferTypesRepository repo;
	
	public List<TransferTypes> getAllTransferTypes(){
		List<TransferTypes> t=new ArrayList<TransferTypes>();
		this.repo.findAll().forEach(tt->t.add(tt));
		return t;
	}
}
