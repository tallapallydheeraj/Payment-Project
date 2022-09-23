package com.dbs.payment.rest;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.AuthenticationRequest;
import com.dbs.payment.model.AuthenticationResponse;
import com.dbs.payment.model.Customer;
import com.dbs.payment.model.ResponsePage;
import com.dbs.payment.service.AccountUserDetailsService;
import com.dbs.payment.service.CustomerService;
import com.dbs.payment.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class LoginRestController {
	@Autowired
	private AccountUserDetailsService userDetailsService;
	
	//private CustomerService cservice;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomerService service; 

	@Autowired
	private JwtUtil jwtUtil;
	
	/*@GetMapping("/user")
	public String user(Principal p)
	{
		//System.out.println("index home page");
		// view name
		return p.getName();
	}*/
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		System.out.println("authenticate ......");
		System.out.println(authenticationRequest.getUsername());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), 
							authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			throw new Exception();
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	@GetMapping("/user")
	public ResponseEntity<ResponsePage> user(HttpServletRequest req) throws UsernameNotFoundException{
		final String authorizationHeader = req.getHeader("Authorization");
	       
        String username = null;
       
        String jwt = null;
       
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
           
            username= jwtUtil.extractUsername(jwt);
        }
        Customer c= this.service.findById(username);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
        		.body(new ResponsePage(200,c.getCustomerid()));
	}
}
