package by.senla.study.board.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.dto.LoginDto;

@RestController
public class LoginController {

	private static final String UNAUTHORIZED = "Error. User is not authorized";
	private static final String AUTHORIZED = "User is authorized";
	
	@PostMapping
	public void login(@RequestBody LoginDto dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetailsService us = new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
	/*	AuthenticationManagerBuilder.

		final String login = authentication.getName();
		final String password = authentication.getCredentials().toString();*/
	}
	
	
}
