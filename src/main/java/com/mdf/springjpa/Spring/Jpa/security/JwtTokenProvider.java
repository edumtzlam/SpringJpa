package com.mdf.springjpa.Spring.Jpa.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	@Value(value = "{App.jwtSecret}")
	private String jwtSecret;

	@Value(value = "{App.jwtExpirationInMs}")
	private String jwtExpirationInMs;

	public String generateToken(Authentication authentication) {
		@SuppressWarnings("unused")
		Object student = authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 360000);
		return Jwts.builder().setSubject("nomapl6@gmail.com").setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
}
