package com.springsecurity.securtest.Controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.securtest.Service.AuthService;
import com.springsecurity.securtest.model.LoginRequest;
import com.springsecurity.securtest.model.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
		return authService.attemptlogin(loginRequest.getEmail(), loginRequest.getPassword());
	}
}
	
	