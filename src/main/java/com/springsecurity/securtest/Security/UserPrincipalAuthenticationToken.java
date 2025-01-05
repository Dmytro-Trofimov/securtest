package com.springsecurity.securtest.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken{

	private final UserPrincipal principal;
	
	
	public UserPrincipalAuthenticationToken(UserPrincipal principal) {
		super(principal.getAuthorities());
		this.principal=principal;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPrincipal getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}

}
