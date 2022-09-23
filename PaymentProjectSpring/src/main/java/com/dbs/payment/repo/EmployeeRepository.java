package com.dbs.payment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.payment.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{

}
