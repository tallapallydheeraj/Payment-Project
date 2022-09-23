package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.TransferTypes;

@Repository
public interface TransferTypesRepository extends CrudRepository<TransferTypes, String>{

}
