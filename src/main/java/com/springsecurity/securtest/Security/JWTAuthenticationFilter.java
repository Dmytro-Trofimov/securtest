package com.springsecurity.securtest.Security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTDecoder decoder;
	@Autowired
	private JWTToPrincipalConverter converter;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("doFilterInternal");
		extractTokenFromRequest(request)
				.map(decoder::decode)
				.map(converter::convert)
				.map(UserPrincipalAuthenticationToken::new)
				.ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
		System.out.println("succes doFilterInternal in filter");
		filterChain.doFilter(request, response);
	}

	private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			System.out.println("filter for lol succes");
			System.out.println(token);
			return Optional.of(token.substring(7));
		}
		System.out.println(token);
		System.out.println("filter for lol error");
		return Optional.empty();
	}

}
