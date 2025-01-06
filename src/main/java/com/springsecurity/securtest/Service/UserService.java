package com.springsecurity.securtest.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springsecurity.securtest.Entity.User;

@Service
public class UserService {

	private static final String EXISTING_EMAIL="test@test.com";
	
	public Optional<User> findByEmail(String email){
		 if(!email.equalsIgnoreCase(EXISTING_EMAIL)) {
			 System.out.println("Bad email");
			 return Optional.empty();
		 }
		 var user = new User();
		 user.setId(1L);
		 user.setEmail(EXISTING_EMAIL);
		 user.setPassword("$2a$12$jYa3p54iDfAdKsBZD9J8Au/vcIk0diIQwnbGuPxZtrr4fJFlIi1jy");
		 user.setRole("ROLE_ADMIN");
		 user.setExtraInfo("my nice admin");
		 return Optional.of(user);
	}
	
}
