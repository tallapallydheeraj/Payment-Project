package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, String> {

}
