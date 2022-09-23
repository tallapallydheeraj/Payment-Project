package com.dbs.payment.rest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Customer;
import com.dbs.payment.service.CustomerService;
import com.dbs.payment.util.JwtUtil;


@RestController
@CrossOrigin(origins = "*")

public class CustomerRestController {
	@Autowired
	private CustomerService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/customer/{cid}")
	public ResponseEntity<Object> getCustomer(@PathVariable String cid,HttpServletRequest req) {
		final String authorizationHeader = req.getHeader("Authorization");
	       
        String username = null;
       
        String jwt = null;
       
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
           
            username= jwtUtil.extractUsername(jwt);
            if(!username.equals(cid))
            	return ResponseEntity.status(HttpStatus.FORBIDDEN)
            			.body("Access Denied");
        }
		try {
			Customer c=this.service.findById(cid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(c);
		}
		catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("customer not found");
		}
	}
	@GetMapping("/receiver/{cid}")
	public ResponseEntity<Object> getReceiver(@PathVariable String cid) {
		try {
			Customer c=this.service.findById(cid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(c);
		}
		catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("customer not found");
		}
	}
	@PostMapping("/customer")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
		if(this.service.updateCustomer(customer)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body("Product updated with id "+customer.getCustomerid());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Product not updated with id "+customer.getCustomerid());
	}
	
}
