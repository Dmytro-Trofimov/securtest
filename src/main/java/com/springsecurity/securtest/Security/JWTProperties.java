package com.springsecurity.securtest.Security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


//просто передаємо кодове слово із пропертів
@Configuration
@ConfigurationProperties("security.jwt")
public class JWTProperties {
	private String secretKey;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
}
