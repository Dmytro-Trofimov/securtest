package com.springsecurity.securtest.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MController {

	@GetMapping("/")
	public String start() {
		return "Bla Bla bLa";
	}
	@GetMapping("/secured")
	public String authTest() { 
		return "if you see this you are authenticated";
	}
}
