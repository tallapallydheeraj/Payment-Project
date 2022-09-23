package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.Logger;

@Repository
public interface LoggerRepository extends CrudRepository<Logger, Integer>{

}
