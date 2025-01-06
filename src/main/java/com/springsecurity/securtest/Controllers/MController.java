package com.springsecurity.securtest.Controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.securtest.Security.UserPrincipal;

@RestController
public class MController {

	@GetMapping("/")
	public String start() {
		return "Bla Bla bLa";
	}
	@GetMapping("/secured")
	public String authTest(@AuthenticationPrincipal UserPrincipal principal) { 
		return principal.getUserId()+principal.getEmail()+principal.getPassword();
	}
}
