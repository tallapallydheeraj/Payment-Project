package com.dbs.payment.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
private String secret_key = "secret";
	
	//retrieve username from jwt token
	public String extractUsername(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}
	
	//retrieve expiration date from jwt token
	public Date extractExpiration(String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	//for retrieveing any information from token we will need the secret key
	private Claims extractAllClaims(String token)
	{
		return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
	}
	//check if the token has expired
	private Boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}
	//generate token for user
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<String, Object>();
		// pass claims as payload
		// subject is username tobe authenticated
		return createToken(claims, userDetails.getUsername());
		
	}
	//while creating the token -
		//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
		//2. Sign the JWT using the HS512 algorithm and secret key.
		//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
		//   compaction of the JWT to a URL-safe string 
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				//1 hours
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*1))
				.signWith(SignatureAlgorithm.HS256, secret_key).compact();
		// compact end of builder pattern
	}
	
	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
