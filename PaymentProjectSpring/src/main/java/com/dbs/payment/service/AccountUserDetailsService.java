package com.dbs.payment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.repo.CustomerUserRepository;


@Service
public class AccountUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomerUserRepository repo;
	
	//@Autowired
	//private PasswordEncoder encode;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("Username "+username);
		Optional<CustomerUser> optional = this.repo.findByCustomerCustomerid(username);
		return optional.map(account ->{
			User user = new User(account.getCustomer().getCustomerid(), account.getUserpassword(),
					new ArrayList<>());
			return user;
			
		}).orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));
		
//		use below code if you are not providing roles	
		
//		return optional.map(account ->{
//			User user = new User(account.getUsername(), account.getPassword(),
//					new ArrayList<>());
//			return user;
//			
//		}).orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));
	}
}
