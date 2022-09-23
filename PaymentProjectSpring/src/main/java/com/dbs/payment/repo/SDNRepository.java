package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;

import com.dbs.payment.model.SDNList;

public interface SDNRepository extends CrudRepository<SDNList, String> {
	
}
