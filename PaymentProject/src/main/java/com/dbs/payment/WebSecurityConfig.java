package com.dbs.payment;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dbs.payment.service.AccountUserDetailsService;
import com.dbs.payment.filter.JwtRequestFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@EnableWebSecurity
@SecurityScheme(scheme = "bearer", 
name="api",
type = SecuritySchemeType.HTTP, 
in = SecuritySchemeIn.HEADER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccountUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	private static final String[] AUTH_WHITELIST = {
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",			
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()//whitelist
		.antMatchers(HttpMethod.GET,AUTH_WHITELIST)
		.anonymous()
		.antMatchers(HttpMethod.POST, "/authenticate")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/message","/swagger-ui.html")
		.permitAll()
		//.antMatchers(HttpMethod.POST,"/transaction").hasAnyRole("ADMIN")
		//.antMatchers("/user").permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.disable().cors();
		 
		 http.addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Autowired
	private JwtRequestFilter jwtFilter;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
}
