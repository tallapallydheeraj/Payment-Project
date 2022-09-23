package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

}
