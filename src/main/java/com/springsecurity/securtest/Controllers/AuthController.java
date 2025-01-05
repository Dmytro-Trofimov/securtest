package com.springsecurity.securtest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.securtest.Security.JWTIssuer;
import com.springsecurity.securtest.model.LoginRequest;
import com.springsecurity.securtest.model.LoginResponse;

@RestController
public class AuthController {
	
	@Autowired
	private JWTIssuer jwtIssuer;
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
		var token = jwtIssuer.issue(1L, loginRequest.getEmail(), List.of("User"));
		return new LoginResponse(token);
	}
}
	