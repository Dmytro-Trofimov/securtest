package com.springsecurity.securtest.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTDecoder {

	@Autowired
	private JWTProperties properties;
	
	public DecodedJWT decode(String token) {
		System.out.println("decode in JWTDecoder");
		return JWT
				.require(Algorithm.HMAC256(properties.getSecretKey()))
				.build().verify(token);
	}
}
