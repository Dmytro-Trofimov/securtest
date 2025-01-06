package com.springsecurity.securtest.Controllers;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.securtest.Security.JWTIssuer;
import com.springsecurity.securtest.Security.UserPrincipal;
import com.springsecurity.securtest.model.LoginRequest;
import com.springsecurity.securtest.model.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final JWTIssuer jwtIssuer;
	private final AuthenticationManager authenticationManager;
	
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		var principal = (UserPrincipal)authentication.getPrincipal();
		var roles = principal.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toList();
		var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
		return new LoginResponse(token);
	}
}
	
	