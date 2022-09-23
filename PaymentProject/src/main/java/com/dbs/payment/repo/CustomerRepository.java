package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

}
