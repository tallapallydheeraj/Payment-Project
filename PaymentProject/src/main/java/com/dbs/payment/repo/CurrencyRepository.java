package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

}
