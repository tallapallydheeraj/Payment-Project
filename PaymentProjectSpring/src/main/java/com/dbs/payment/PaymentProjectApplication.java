package com.dbs.payment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dbs.payment.filter.JwtRequestFilter;
import com.dbs.payment.model.Bank;
import com.dbs.payment.model.Customer;
import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.repo.BankRepository;
import com.dbs.payment.repo.CustomerRepository;
import com.dbs.payment.repo.CustomerUserRepository;
import com.dbs.payment.service.CustomerService;
import com.dbs.payment.util.JwtUtil;


@SpringBootApplication
public class PaymentProjectApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentProjectApplication.class, args);
		//System.out.println("hi");
		//initialize();
	}
	@Autowired
	private CustomerUserRepository repo;
	@Autowired
	private CustomerService service;
	
	//private BCryptPasswordEncoder encoder;
	/*@PostConstruct
	//@Bean
	public void initialize() {
		System.out.println("initialize");
		CustomerUser user=new CustomerUser(2,"Shalini Mittal", this.service.findById("69652133523248"), new BCryptPasswordEncoder().encode("shalini"));

		repo.save(user);
		System.out.println(repo.count());
		for(CustomerUser acc:repo.findAll())
			System.out.println(acc);
	}*/
	
	@Bean
	public JwtUtil utility() {
		return new JwtUtil();
	}
	

}
