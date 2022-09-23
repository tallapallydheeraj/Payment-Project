package com.dbs.payment.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.CustomerUser;
@Repository
public interface CustomerUserRepository extends CrudRepository<CustomerUser, Integer>{
	
	public Optional<CustomerUser> findByCustomerCustomerid(String id);
}
